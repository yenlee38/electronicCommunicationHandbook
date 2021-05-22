package com.example.electroniccommunicationhandbook.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Point;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.service.PointService;

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

public class PointRepository {

    private static final String BASE_URL = "https://api-spring-handbook.herokuapp.com/";

    private PointService pointSearchService;
    public Student student;

    private static PointRepository instance;

    public static PointRepository getInstance(){
        if(instance== null){
            instance= new PointRepository();
        }
        return instance;
    }

    public PointRepository() {

        MainRepository mainRepository;
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                       "Bearer " +MainRepository.getToken());

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        pointSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PointService.class);
    }

    public Student createPointInfo(String id) {

        pointSearchService.findStudent(id).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful())
                {
                    student= response.body();
                    Log.e("ID :", student.getStudentId() );
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                student= null;
                Log.e("Failure : ", t.toString() );
            }
        });
        return  student;
    }



}
