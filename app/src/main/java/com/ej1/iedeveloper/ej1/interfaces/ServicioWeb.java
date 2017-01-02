package com.ej1.iedeveloper.ej1.interfaces;

import com.ej1.iedeveloper.ej1.model.ClientsResponse;
import com.ej1.iedeveloper.ej1.model.LoginResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by iedeveloper on 20/12/16.
 */

public interface ServicioWeb  {

    @FormUrlEncoded
    @POST("movil/login")
    Call<LoginResponse> login(@Field("email") String userName, @Field("password") String password);

    @GET("clientes")
    Call<List<ClientsResponse>> getClients(@Header("Authorization") String token);
}
