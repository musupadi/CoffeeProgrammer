package com.destinyapp.coffeeprogrammer.API;

import com.destinyapp.coffeeprogrammer.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("user/Login")
    Call<ResponseModel> Login(@Field("email") String email,
                            @Field("password") String password);
}
