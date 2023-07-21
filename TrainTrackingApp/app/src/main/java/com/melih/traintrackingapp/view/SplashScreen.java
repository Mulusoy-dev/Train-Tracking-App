package com.melih.traintrackingapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.melih.traintrackingapp.R;

public class SplashScreen extends AppCompatActivity {

    TextView AppName;
    LottieAnimationView lottie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        AppName= findViewById(R.id.AppName);
        lottie= findViewById(R.id.lottie);
        AppName.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        lottie.animate().setDuration(4000).setStartDelay(2900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        },5000);
    }
}