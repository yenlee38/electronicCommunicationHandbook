package com.example.electroniccommunicationhandbook.repository;

import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.service.AuthenticateService;
import com.example.electroniccommunicationhandbook.service.ClassService;
import com.example.electroniccommunicationhandbook.service.PointService;
import com.example.electroniccommunicationhandbook.service.RequestPaperService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class MainRepository {
   private static MainRepository instance;
   private PointService pointService;

    public RequestPaperService getRequestPaperService() {
        return requestPaperService;
    }

    private RequestPaperService requestPaperService;
    private Student student;
    private boolean loginSuccess= false;
   private Retrofit retrofit;
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

    public static String token=" ";

    public static MainRepository getInstance(){
        if(instance== null){
            instance= new MainRepository();
        }
        return instance;
    }
    public MainRepository(){
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setPrettyPrinting()
                .setLenient()
                .create();
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

        retrofit= new Retrofit.Builder()
                .baseUrl("https://apihandbookversion2.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        authenticateService= retrofit.create(AuthenticateService.class);
        pointService= retrofit.create(PointService.class);
        requestPaperService=retrofit.create(RequestPaperService.class);
        classService= retrofit.create(ClassService.class);
    }

    public ClassService getClassService() {
        return classService;
    }

    public void setClassService(ClassService classService) {
        this.classService = classService;
    }

    public ClassService classService;
    public void setRequestPaperService(RequestPaperService requestPaperService) {
        this.requestPaperService = requestPaperService;
    }


//Must return role
//    public boolean login (Account account){
//
//        authenticateService.login(account).enqueue(new Callback<jwt>() {
//            @Override
//            public void onResponse(Call<jwt> call, retrofit2.Response<jwt> auth) {
//                if(auth != null){
//                    Log.e("token",auth.body().getJwt()  );
//                    String token=auth.body().getJwt();
//
//                    //Save token
//                   /* SharedPreferences.Editor prefsEditor = getSharePreference(MY_PREFS_FILE, MODE_PRIVATE).edit();
//                    prefsEditor.putString("token",token);
//                    prefsEditor.commit();*/
//
//                    setToken(token);
//                    loginSuccess= true;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<jwt> call, Throwable t) {
//
//                loginSuccess= false;
//                Log.e("Fail", t.toString() );
//            }
//        });
//        return  loginSuccess;
//    }



}
