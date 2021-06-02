package com.example.electroniccommunicationhandbook.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Announcement;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.service.AnnouncementService;
import com.example.electroniccommunicationhandbook.service.ClassService;
import com.example.electroniccommunicationhandbook.util.Comon;
import com.google.android.gms.common.internal.service.Common;

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

public class ClassRepository extends AndroidViewModel {
    private static final String BASE_URL = Comon.API_LINK;
    private static ClassRepository instance;
    private ClassService classService;
    private ArrayList<Class> announcements;
    private Context context;
    private MutableLiveData<ArrayList<Class>> classList;

    public static ClassRepository getInstance(Application application){
        if(instance== null){
            instance= new ClassRepository(application);
        }
        return instance;
    }

    public ClassRepository(Application application){
        super(application);
        classList= new MutableLiveData<>();
        context= application.getApplicationContext();
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

        classService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ClassService.class);
    }

    public void  getClassOfTeacher(String teacherId, int year, int semester){

        classService.getClassOfTeacher(teacherId,semester,year).enqueue(new Callback<ArrayList<Class>>() {
            @Override
            public void onResponse(Call<ArrayList<Class>> call, Response<ArrayList<Class>> response) {
                if(response.isSuccessful())
                {
                    classList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Class>> call, Throwable t) {
                Log.e("Failure ", t.toString() );
            }
        });
    }
    public MutableLiveData<ArrayList<Class>> getClassList() {
        return classList;
    }
}
