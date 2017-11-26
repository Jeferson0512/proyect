package com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.Services;

import com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.Model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Usuario on 25/11/2017.
 */

public interface ApiService {

    String API_BASE_URL = "https://presentar-project-cruz-jeferson-0512.cs50.io";

    //--------------------------USUARIO-----------------------------//
    @GET("/Clinica/v1/user_listado")
    Call<List<Usuario>> getUsuario();

    @FormUrlEncoded
    @POST("/Clinica/v1/user_login")
    Call<ResponseMessage> loginUsuario(@Field("username") String username,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("/Clinica/v1/user_create")
    Call<ResponseMessage> createUsuario(@Field("username") String username,
                                        @Field("password") String password,
                                        @Field("type") String type,
                                        @Field("reg_date") String reg_date);


}
