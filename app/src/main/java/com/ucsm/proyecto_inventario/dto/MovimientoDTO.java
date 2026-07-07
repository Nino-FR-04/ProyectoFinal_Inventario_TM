package com.ucsm.proyecto_inventario.dto;

public class MovimientoDTO {

    private int cantidad;
    private String tipo;

    public MovimientoDTO() {
    }

    public MovimientoDTO(int cantidad, String tipo) {
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}