package com.files.blocodenotas.Api;

import com.files.blocodenotas.Modelo.Nota;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
        @FormUrlEncoded
        @POST("salvar.php")
        Call<Nota> saveNota(
                @Field("titulo") String titulo,
                @Field("nota") String nota,
                @Field("cor") int cor
        );

        @GET("notas.php")
        Call<List<Nota>>getNotas();

        @FormUrlEncoded
        @POST("atualiza.php")
        Call<Nota> updateNota(
                @Field("id") int id,
                @Field("titulo") String titulo,
                @Field("nota") String nota,
                @Field("cor") int cor
        );

        @FormUrlEncoded
        @POST("deleta.php")
        Call<Nota> deleteNota(@Field("id") int id);

}