package com.example.electroniccommunicationhandbook.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.service.PointService;
import com.example.electroniccommunicationhandbook.service.StudentService;

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

public class StudentRepository {
    private static final String BASE_URL = "https://api-spring-handbook.herokuapp.com/";

    private StudentService studentService;
    public Student student;
    private MutableLiveData<Class> classResponseLiveData;

    private static StudentRepository instance;

    public static StudentRepository getInstance(){
        if(instance== null){
            instance= new StudentRepository();
        }
        return instance;
    }

    public StudentRepository() {

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

        studentService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StudentService.class);
    }

    public Student getInfo(String id) {

        studentService.getInfo(id).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful())
                {
                    student = response.body();
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

    public MutableLiveData<Class> getSchedule(String idStudent, int year, int semester) {
       studentService.getSchedule(idStudent, year, semester)
               .enqueue(new Callback<Class>() {
                   @Override
                   public void onResponse(Call<Class> call, Response<Class> response) {
                        if(response.body() !=null){
                            classResponseLiveData.postValue(response.body());
                        }
                   }

                   @Override
                   public void onFailure(Call<Class> call, Throwable t) {
                        classResponseLiveData.postValue(null);
                   }
               });

       return classResponseLiveData;
    }

    public MutableLiveData<Class> getClassResponseLiveData() {
        return classResponseLiveData;
    }
}
