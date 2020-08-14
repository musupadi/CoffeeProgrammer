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
    Call<ResponseModel> Login(@Field("email_users") String email_users,
                            @Field("password_users") String password_users);

    @FormUrlEncoded
    @POST("user/UsersData")
    Call<ResponseModel> UsersData(@Field("id_users") String id_users);

    @FormUrlEncoded
    @POST("module/SubModul")
    Call<ResponseModel> SubModel(@Field("id_modul") String id_modul);

    @FormUrlEncoded
    @POST("module/Bab")
    Call<ResponseModel> Bab(@Field("id_sub_modul") String id_sub_modul);

    @FormUrlEncoded
    @POST("module/SubBab")
    Call<ResponseModel> SubBab(@Field("id_bab") String id_bab);
}
