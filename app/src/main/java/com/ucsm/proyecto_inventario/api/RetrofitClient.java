package com.ucsm.proyecto_inventario.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // Cambia por la IP de tu servidor
    private static final String BASE_URL =
            "http://172.16.10.31:30050/api/";

    private static Retrofit retrofit;

    public static Retrofit getClient(){

        if(retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;

    }

}