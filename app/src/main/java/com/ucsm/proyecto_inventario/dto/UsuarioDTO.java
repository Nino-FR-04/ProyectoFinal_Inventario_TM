package com.ucsm.proyecto_inventario.dto;

public class UsuarioDTO {

    private int id_usuario;
    private String nombre;
    private String email;
    private String password;
    private String rol;

    public UsuarioDTO() {
    }

    public UsuarioDTO(int id_usuario, String nombre, String email, String password, String rol) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}