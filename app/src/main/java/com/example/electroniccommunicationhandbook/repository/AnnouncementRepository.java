package com.example.electroniccommunicationhandbook.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.service.AnnouncementService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnnouncementRepository extends AndroidViewModel {

    private static final String BASE_URL = "https://api-spring-handbook.herokuapp.com/";
    private static AnnouncementRepository instance;
    private AnnouncementService announcementService;
    private MutableLiveData<ArrayList<com.example.electroniccommunicationhandbook.entity.Announcement>> announcmentList;

    public AnnouncementRepository(@NonNull Application application) {

        super(application);
        announcmentList= new MutableLiveData<>();

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

        announcementService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AnnouncementService.class);
    }

    public static AnnouncementRepository getInstance(Application application){
        if(instance== null){
            instance= new AnnouncementRepository(application);
        }
        return instance;
    }

    


}
