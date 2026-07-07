package com.ucsm.proyecto_inventario.repository;

import com.ucsm.proyecto_inventario.api.ApiService;
import com.ucsm.proyecto_inventario.api.RetrofitClient;
import com.ucsm.proyecto_inventario.dto.MovimientoDTO;
import com.ucsm.proyecto_inventario.dto.ProductoDTO;

import java.util.List;

import retrofit2.Call;

public class ProductoRepository {

    private final ApiService api;

    public ProductoRepository(){

        api = RetrofitClient
                .getClient()
                .create(ApiService.class);

    }

    public Call<List<ProductoDTO>> listarProductos(){
        return api.listarProductos();
    }

    public Call<Void> registrarProducto(ProductoDTO producto){
        return api.registrarProducto(producto);
    }

    public Call<Void> editarProducto(int id,ProductoDTO producto){
        return api.editarProducto(id,producto);
    }

    public Call<ProductoDTO> obtenerProducto(int id){

        return api.obtenerProducto(id);

    }

    public Call<Void> actualizarStock(int id, MovimientoDTO movimiento){

        return api.actualizarStock(id, movimiento);

    }

}