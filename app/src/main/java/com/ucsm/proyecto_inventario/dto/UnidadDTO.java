package com.ucsm.proyecto_inventario.dto;

public class UnidadDTO {

    private int id_unidad;
    private String nombre;
    private String abreviatura;

    public UnidadDTO() {
    }

    public UnidadDTO(int id_unidad, String nombre, String abreviatura) {
        this.id_unidad = id_unidad;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }

    public int getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(int id_unidad) {
        this.id_unidad = id_unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
}