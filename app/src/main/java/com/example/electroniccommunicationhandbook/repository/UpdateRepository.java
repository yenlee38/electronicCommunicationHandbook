package com.example.electroniccommunicationhandbook.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Dummy.Role;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.service.AccountService;

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

public class UpdateRepository extends AndroidViewModel {

    private static final String BASE_URL = "https://apihandbookversion2.herokuapp.com/";
    //private static final String BASE_URL = "http://localhost:8080/";
    private Account account;
    private AccountService accountService;
    private Role role;
    private static PointRepository instance;
    private MutableLiveData<Role> roleLiveData;
    public Account getAccount() {
        return getAccount();
    }
    public static PointRepository getInstance(Application application){
        if(instance== null){
            instance= new PointRepository(application);
        }
        return instance;
    }

    public UpdateRepository(Application app) {
        super(app);
        role = new Role();
        roleLiveData = new MutableLiveData<>();
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

        accountService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AccountService.class);
    }

    public int UpdateInfo(int accountID, String address,String phone){

        accountService.updateInfo(accountID,address,phone).enqueue(new Callback<Role>() {
            @Override
            public void onResponse(Call<Role> call, Response<Role> response) {
                if(response.isSuccessful()){
                    Log.e("new:", response.body().getRole() + "");
                    role = response.body();
                }
            }

            @Override
            public void onFailure(Call<Role> call, Throwable t) {

            }
        });
        return role.getRole();
    }
    public void ResetPassword(String uPhone,String uPassword){
        accountService.resetPassword(uPhone,uPassword).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Log.e("reset :",response.body().getPassword());
                account = response.body();
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });

    }

    public int CheckPhoneNumber(String phone){
        accountService.checkNumberPhone(phone).enqueue(new Callback<Role>() {
            @Override
            public void onResponse(Call<Role> call, Response<Role> response) {
                Log.e("new:", response.body().getRole() + "");
                role = response.body();
            }

            @Override
            public void onFailure(Call<Role> call, Throwable t) {

            }
        });
        return role.getRole();
    }


    public MutableLiveData<Role> CheckPhoneNumberLiveData(String phone){
        accountService.checkNumberPhone(phone).enqueue(new Callback<Role>() {
            @Override
            public void onResponse(Call<Role> call, Response<Role> response) {
                Log.e("new:", response.body().getRole() + "");
                roleLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Role> call, Throwable t) {

            }
        });
        return roleLiveData;
    }




}
