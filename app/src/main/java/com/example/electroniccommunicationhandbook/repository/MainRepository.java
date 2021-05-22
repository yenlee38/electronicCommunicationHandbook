package com.example.electroniccommunicationhandbook.repository;

import com.example.electroniccommunicationhandbook.service.AuthenticateService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class MainRepository {
   private static MainRepository instance;

    public AuthenticateService getAuthenticateService() {
        return authenticateService;
    }

    private AuthenticateService authenticateService;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MainRepository.token = token;
    }

    private static String token=" ";

    public static MainRepository getInstance(){
        if(instance== null){
            instance= new MainRepository();
        }
        return instance;
    }
    public MainRepository(){
        HttpLoggingInterceptor interceptor= new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {

                Request newRequest= chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer "+token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api-spring-handbook.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        authenticateService= retrofit.create(AuthenticateService.class);

    }
}
