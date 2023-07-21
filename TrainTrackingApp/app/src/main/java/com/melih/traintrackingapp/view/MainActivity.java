package com.melih.traintrackingapp.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;


import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melih.traintrackingapp.R;
import com.melih.traintrackingapp.adapter.RecyclerViewAdapter;
import com.melih.traintrackingapp.model.TrainModel;
import com.melih.traintrackingapp.service.TrainAPI;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    ArrayList<TrainModel> trainModels;
    private String BASE_URL = "https://backend-train.onrender.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;


    // RxJava CompositeDisposable tanımlama
    // Call 'ların bir süre sonra hafızadan silinmesi gerekir çünkü hafızayı daha verimli kullanılmasını istiyoruz.
    CompositeDisposable compositeDisposable;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        // URL BASE -> https://backend-train.onrender.com/
        // GET -> data
        // https://backend-train.onrender.com/data


        // RecyclerView tanımlama işlemleri
        recyclerView = findViewById(R.id.recyclerView);


        // Retrofit & JSON
        // Alınacak verilerin JSON tip uyumluluğunu sağlar.
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();

    }

    private void loadData() {
        // Servis oluşturuldu
        final TrainAPI trainAPI = retrofit.create(TrainAPI.class);

        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(Observable.interval(0, 10, TimeUnit.SECONDS)
                .flatMap(aLong -> trainAPI.getData())
                .subscribeOn(Schedulers.io())                                // Hangi thread'de kayıt olma işleminin yapılacağını belirtilir.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, throwable -> {
                    throwable.printStackTrace();
                }));                                               // Ele alma metodu

        /*


        // Verileri 'Call' metodu ile çekiyoruz. Başka metotlarda var.
        Call<List<TrainModel>> call = trainAPI.getData();

        //enqueue ile asenkron veri çekme isteği gerçekleştiriyoruz.
        call.enqueue(new Callback<List<TrainModel>>() {
            @Override
            public void onResponse(Call<List<TrainModel>> call, Response<List<TrainModel>> response) {
                if (response.isSuccessful()) {   // Gelen Cevap Başarılı ise
                    List<TrainModel> responseList = response.body();

                    // Başta tanımlanan 'trainModels' burada tanımlanır.
                    trainModels = new ArrayList<>(responseList);

                    // RecyclerView işlemleri
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(trainModels);
                    recyclerView.setAdapter(recyclerViewAdapter);


                }
            }

            @Override
            public void onFailure(Call<List<TrainModel>> call, Throwable t) {
                // Bir hata varsa ne yapılacağı burada belirtilir
                t.printStackTrace();
            }
        });

         */

    }


    private void handleResponse(List<TrainModel> trainModelList) {


        // Başta tanımlanan 'trainModels' burada tanımlanır.
        trainModels = new ArrayList<>(trainModelList);

        // RecyclerView işlemleri
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(trainModels);
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}
