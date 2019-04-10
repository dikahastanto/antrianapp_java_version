package com.dikahastanto.antrian.api;

import com.dikahastanto.antrian.models.AntrianResponse;
import com.dikahastanto.antrian.models.DefaultResponse;
import com.dikahastanto.antrian.models.LoginResponse;
import com.dikahastanto.antrian.models.NoAntrianResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("nama_lengkap") String nama_lengkap
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("allantrian")
    Call<AntrianResponse> getDatas_antrian();

    @GET("getnoantrian")
    Call<NoAntrianResponse> getData_antrian();

    @FormUrlEncoded
    @POST("insertnoantrian")
    Call<DefaultResponse> insertnoAntrian(
            @Field("no_panggil") String no_panggil,
            @Field("username") String username,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("jam") String jam
    );

    @FormUrlEncoded
    @POST("getnoantrianbyusername")
    Call<NoAntrianResponse> getnoantrianbyusername(
            @Field("username") String username
    );

    @DELETE("deletnoantrian/{id}")
    Call<DefaultResponse> deletnoantrian(@Path("id") String id);

}
