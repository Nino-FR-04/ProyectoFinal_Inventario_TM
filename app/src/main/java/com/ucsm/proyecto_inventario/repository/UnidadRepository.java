package com.ucsm.proyecto_inventario.repository;

import com.ucsm.proyecto_inventario.api.ApiService;
import com.ucsm.proyecto_inventario.api.RetrofitClient;
import com.ucsm.proyecto_inventario.dto.UnidadDTO;

import java.util.List;

import retrofit2.Call;

public class UnidadRepository {

    private final ApiService api;

    public UnidadRepository(){

        api = RetrofitClient
                .getClient()
                .create(ApiService.class);

    }

    public Call<List<UnidadDTO>> listar(){

        return api.listarUnidades();

    }

}