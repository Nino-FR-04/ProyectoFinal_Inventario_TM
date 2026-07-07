package com.ucsm.proyecto_inventario.dto;

public class ProductoDTO {

    private int id_producto;
    private String codigo;
    private String descripcion;
    private double precio;
    private int stock;
    private int id_unidad;
    private String unidad;

    public ProductoDTO() {
    }

    public ProductoDTO(int id_producto, String codigo, String descripcion,
                       double precio, int stock, int id_unidad, String unidad) {

        this.id_producto = id_producto;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.id_unidad = id_unidad;
        this.unidad = unidad;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(int id_unidad) {
        this.id_unidad = id_unidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}