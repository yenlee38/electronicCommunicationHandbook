package com.example.electroniccommunicationhandbook.repository;

import android.util.Log;

import com.example.electroniccommunicationhandbook.entity.OffRequest;
import com.example.electroniccommunicationhandbook.service.OffRequestService;
import com.example.electroniccommunicationhandbook.service.StudentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class OffRequestRepository {

    private static final String BASE_URL = "https://apihandbookversion2.herokuapp.com/";
    private static OffRequestRepository instance;
    private OffRequestService offRequestService;
    public OffRequest moffRequest;

    public static OffRequestRepository getInstance(){
        if(instance== null){
            instance= new OffRequestRepository();
        }
        return instance;
    }

    public OffRequestRepository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        "Bearer " + MainRepository.getToken());

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson = gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setPrettyPrinting().setLenient().create();

        offRequestService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(OffRequestService.class);
    }

    public OffRequest save(OffRequest offRequest){
        moffRequest= new OffRequest();
        offRequestService.save(offRequest).enqueue(new Callback<OffRequest>() {
            @Override
            public void onResponse(Call<OffRequest> call, Response<OffRequest> response) {
                if(response.isSuccessful())
                {
                    moffRequest= response.body();
                    Log.e("dg", "onResponse:"+response.body() );
                }
            }

            @Override
            public void onFailure(Call<OffRequest> call, Throwable t) {
                Log.e("f", "onFailure: "+t.toString() );
                moffRequest= null;
            }
        });
        return  moffRequest;
    }
}
