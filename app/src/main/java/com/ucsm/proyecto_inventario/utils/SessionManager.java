package com.ucsm.proyecto_inventario.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "inventario_pref";

    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_CORREO = "correo";
    private static final String KEY_RECORDAR = "recordar";

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context){

        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public void guardarSesion(int id,String nombre,String correo,boolean recordar){

        editor.putInt(KEY_ID,id);
        editor.putString(KEY_NOMBRE,nombre);
        editor.putString(KEY_CORREO,correo);
        editor.putBoolean(KEY_RECORDAR,recordar);
        editor.apply();

    }

    public boolean estaLogueado(){

        return preferences.getBoolean(KEY_RECORDAR,false);

    }

    public int getId(){

        return preferences.getInt(KEY_ID,0);

    }

    public String getNombre(){

        return preferences.getString(KEY_NOMBRE,"");

    }

    public String getCorreo(){

        return preferences.getString(KEY_CORREO,"");

    }

    public void cerrarSesion(){

        editor.clear();
        editor.apply();

    }

}