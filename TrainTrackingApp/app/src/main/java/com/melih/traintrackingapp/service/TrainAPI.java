package com.melih.traintrackingapp.service;

import com.melih.traintrackingapp.model.TrainModel;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.http.GET;

public interface TrainAPI {

    //GET, POST, UPDATE, DELETE

    // URL BASE -> https://backend-train.onrender.com/
    // GET -> data
    // https://backend-train.onrender.com/data

    @GET("data")
    Observable<List<TrainModel>> getData();



    //Call<List<TrainModel>> getData();

}
