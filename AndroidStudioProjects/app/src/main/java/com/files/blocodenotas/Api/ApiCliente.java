package com.files.blocodenotas.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCliente {

    private static final String BASE_URL = "https://bloco-de-notas-app.000webhostapp.com/";
    private static Retrofit retrofit;

    public static Retrofit getApiCliente(){

            if (retrofit == null){
                retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            }

        return retrofit;
    }
}
