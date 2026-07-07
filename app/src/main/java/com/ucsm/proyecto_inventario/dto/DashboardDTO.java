package com.ucsm.proyecto_inventario.dto;

public class DashboardDTO {

    private int totalProductos;
    private int stockBajo;
    private int sinStock;

    public DashboardDTO() {
    }

    public int getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(int totalProductos) {
        this.totalProductos = totalProductos;
    }

    public int getStockBajo() {
        return stockBajo;
    }

    public void setStockBajo(int stockBajo) {
        this.stockBajo = stockBajo;
    }

    public int getSinStock() {
        return sinStock;
    }

    public void setSinStock(int sinStock) {
        this.sinStock = sinStock;
    }
}