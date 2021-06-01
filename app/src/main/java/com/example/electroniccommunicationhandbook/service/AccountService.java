package com.example.electroniccommunicationhandbook.service;

import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Dummy.Role;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountService {
    //Update thông tin
    @POST("/account/{accountId}/address/{address}/phone/{phone}")
    Call<Role> updateInfo(@Path("accountId") int accountId, @Path("address") String address, @Path("phone") String phone);

    //Kiểm tra số điện thoại đã tồn tại hay chưa
    @GET("/account/phone/{phone}")
    Call<Role> checkNumberPhone(@Path("phone") String phone);

    //Reset Password
    @POST("/account/phone/{uPhone}/password{uPassword}")
    Call<Account> resetPassword(@Path("uPhone") String uPhone, @Path("uPassword") String uPassword);


}


