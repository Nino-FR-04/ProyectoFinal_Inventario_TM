package com.ucsm.proyecto_inventario.api;

import com.ucsm.proyecto_inventario.dto.DashboardDTO;
import com.ucsm.proyecto_inventario.dto.MovimientoDTO;
import com.ucsm.proyecto_inventario.dto.ProductoDTO;
import com.ucsm.proyecto_inventario.dto.UnidadDTO;
import com.ucsm.proyecto_inventario.dto.UsuarioDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("productos")
    Call<List<ProductoDTO>> listarProductos();

    @GET("unidades")
    Call<List<UnidadDTO>> listarUnidades();

    @POST("productos")
    Call<Void> registrarProducto(@Body ProductoDTO producto);

    @PUT("productos/{id}")
    Call<Void> editarProducto(
            @Path("id") int id,
            @Body ProductoDTO producto
    );

    @GET("productos/{id}")
    Call<ProductoDTO> obtenerProducto(
            @Path("id") int id
    );

    @PUT("productos/stock/{id}")
    Call<Void> actualizarStock(
            @Path("id") int id,
            @Body MovimientoDTO movimiento
    );

    // LOGIN
    @POST("login")
    Call<UsuarioDTO> login(@Body UsuarioDTO usuario);

    // ESTADÍSTICAS
    @GET("dashboard")
    Call<DashboardDTO> obtenerDashboard();
}