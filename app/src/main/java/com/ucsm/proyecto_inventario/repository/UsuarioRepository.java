package com.ucsm.proyecto_inventario.repository;

import com.ucsm.proyecto_inventario.api.ApiService;
import com.ucsm.proyecto_inventario.api.RetrofitClient;
import com.ucsm.proyecto_inventario.dto.UsuarioDTO;

import retrofit2.Call;

public class UsuarioRepository {

    private final ApiService api;

    public UsuarioRepository(){

        api = RetrofitClient
                .getClient()
                .create(ApiService.class);

    }

    public Call<UsuarioDTO> login(UsuarioDTO usuario){

        return api.login(usuario);

    }

}