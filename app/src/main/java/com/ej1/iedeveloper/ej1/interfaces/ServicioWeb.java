package com.ej1.iedeveloper.ej1.interfaces;

import com.ej1.iedeveloper.ej1.model.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by iedeveloper on 20/12/16.
 */

public interface ServicioWeb  {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email") String userName, @Field("password") String password);
}
