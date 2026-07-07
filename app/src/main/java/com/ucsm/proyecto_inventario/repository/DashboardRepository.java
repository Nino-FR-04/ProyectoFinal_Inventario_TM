package com.ucsm.proyecto_inventario.repository;

import com.ucsm.proyecto_inventario.api.ApiService;
import com.ucsm.proyecto_inventario.api.RetrofitClient;
import com.ucsm.proyecto_inventario.dto.DashboardDTO;

import retrofit2.Call;

public class DashboardRepository {

    private final ApiService api;

    public DashboardRepository(){

        api = RetrofitClient
                .getClient()
                .create(ApiService.class);

    }

    public Call<DashboardDTO> obtenerDashboard(){

        return api.obtenerDashboard();

    }

}