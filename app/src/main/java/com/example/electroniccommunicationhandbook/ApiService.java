package com.example.electroniccommunicationhandbook;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface ApiService {

    //Gson converter
    Gson gson = new GsonBuilder().setDateFormat("yyy-MM-dd H:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://api-spring-handbook.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

}
