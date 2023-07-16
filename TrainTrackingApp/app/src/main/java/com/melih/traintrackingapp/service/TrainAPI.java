package com.melih.traintrackingapp.service;

import com.melih.traintrackingapp.model.TrainModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrainAPI {

    //GET, POST, UPDATE, DELETE

    // URL BASE -> https://backend-train.onrender.com/
    // GET -> data
    // https://backend-train.onrender.com/data

    @GET("data")
    Call<List<TrainModel>> getData();

}
