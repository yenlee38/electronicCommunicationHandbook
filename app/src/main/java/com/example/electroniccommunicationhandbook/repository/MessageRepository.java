package com.example.electroniccommunicationhandbook.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.service.MessageService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageRepository {
    private MessageService messageService;
    private MutableLiveData<ArrayList<Message>> messageList;
    private static MessageRepository instance;

    public MessageRepository(){
        messageList= new MutableLiveData<>();

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

        messageService = new retrofit2.Retrofit.Builder()
                .baseUrl("https://api-spring-handbook.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MessageService.class);
    }



    public static MessageRepository getInstance(){
        if(instance== null){
            instance= new MessageRepository();
        }
        return instance;
    }

    /*
    get all message sent by person
     */
    public MutableLiveData<ArrayList<Message>> getMessageByAccountID(int accountID){
//        messageService.getMessageByAccountID(accountID).enqueue(new Callback<List<Message>>() {
//            @Override
//            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
//                if(response.isSuccessful()){
//                    messageList.postValue(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Message>>> call, Throwable t) {
//                messageList=null;
//                Log.e("Mess", "Get message list is fail");
//            }
//        });
//        return messageList;
        messageService.getMessageByAccountID(accountID).enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                if(response.isSuccessful()){
                    messageList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                messageList=null;
                Log.e("Mess", "Get list is fail");
            }
        });
        return messageList;
    }

}
