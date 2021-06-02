package com.example.electroniccommunicationhandbook.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Announcement;
import com.example.electroniccommunicationhandbook.service.AnnouncementService;
import com.example.electroniccommunicationhandbook.util.Comon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnnouncementRepository extends AndroidViewModel {

    private static final String BASE_URL = Comon.API_LINK;
    private static AnnouncementRepository instance;
    private AnnouncementService announcementService;
    private ArrayList<Announcement> announcements;
    private MutableLiveData<ArrayList<Announcement>> announcementList;
    private MutableLiveData<ArrayList<Announcement>> announcementSchools;
    private Announcement mAnnouncement;

    public AnnouncementRepository(@NonNull Application application) {

        super(application);
        announcementList= new MutableLiveData<>();
        announcementSchools= new MutableLiveData<>();

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

        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson = gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setPrettyPrinting().setLenient().create();

        announcementService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(AnnouncementService.class);
    }

    public static AnnouncementRepository getInstance(Application application){
        if(instance== null){
            instance= new AnnouncementRepository(application);
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Announcement>> findAnnouncementByStudentId(String studentId){
        announcements= new ArrayList<>();
        announcementService.findByStudentId(studentId).enqueue(new Callback<ArrayList<Announcement>>() {
            @Override
            public void onResponse(Call<ArrayList<Announcement>> call, Response<ArrayList<Announcement>> response) {
                if(response.isSuccessful())
                {
                    announcements= response.body();
                    announcementList.postValue(announcements);
                    Log.e("response", "onResponse: "+announcements.size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Announcement>> call, Throwable t) {
                Log.e("Failure : ", "onFailure: "+t.toString() );
            }
        });
        //Log.e("Size", ""+announcmentList.getValue().size() );
        return  announcementList;

    }

    public Announcement save(Announcement announcement){
        mAnnouncement= new Announcement();
        announcementService.save(announcement).enqueue(new Callback<Announcement>() {
            @Override
            public void onResponse(Call<Announcement> call, Response<Announcement> response) {
                if(response.isSuccessful())
                {
                    mAnnouncement= response.body();
                    Log.e("DOne", "onResponse: "+response.body() );
                }
            }

            @Override
            public void onFailure(Call<Announcement> call, Throwable t) {
                mAnnouncement= null;
                Log.e("Faile", "onFailure: " +t.toString() );
            }
        });

        return mAnnouncement;
    }

    public void findByStudentIdAndSenderNull(String studentId){
        announcementService.findByStudentIdAndSenderNull(studentId).enqueue(new Callback<ArrayList<Announcement>>() {
            @Override
            public void onResponse(Call<ArrayList<Announcement>> call, Response<ArrayList<Announcement>> response) {
                if(response.isSuccessful()){
                    announcementSchools.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Announcement>> call, Throwable t) {
                Log.e("Failure : ", "onFailure: "+t.toString() );
            }
        });

    }

    public void findBySenderId(String senderId){
        announcementService.findBySenderId(senderId).enqueue(new Callback<ArrayList<Announcement>>() {
            @Override
            public void onResponse(Call<ArrayList<Announcement>> call, Response<ArrayList<Announcement>> response) {
                if(response.isSuccessful()){
                  announcementList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Announcement>> call, Throwable t) {
                Log.e("Failure : ", "onFailure: "+t.toString() );
            }
        });

    }



    public MutableLiveData<ArrayList<Announcement>> getAnnouncementSchools() {
        return announcementSchools;
    }

    public MutableLiveData<ArrayList<Announcement>> getAnnouncmentList() {
        return announcementList;
    }
}
