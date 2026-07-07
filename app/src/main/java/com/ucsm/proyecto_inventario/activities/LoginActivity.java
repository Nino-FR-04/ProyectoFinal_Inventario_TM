package com.ucsm.proyecto_inventario.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.ucsm.proyecto_inventario.R;
import com.ucsm.proyecto_inventario.dto.UsuarioDTO;
import com.ucsm.proyecto_inventario.repository.UsuarioRepository;
import com.ucsm.proyecto_inventario.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etCorreo;
    private TextInputEditText etPassword;

    private MaterialCheckBox chkRecordar;

    private MaterialButton btnLogin;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        if(sessionManager.estaLogueado()){

            startActivity(new Intent(this,MainActivity.class));
            finish();

        }

        etCorreo=findViewById(R.id.etCorreo);
        etPassword=findViewById(R.id.etPassword);
        chkRecordar=findViewById(R.id.chkRecordar);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v->login());

    }

    private void login(){

        UsuarioDTO usuario = new UsuarioDTO();

        usuario.setEmail(etCorreo.getText().toString());

        usuario.setPassword(etPassword.getText().toString());

        UsuarioRepository repository = new UsuarioRepository();

        repository.login(usuario).enqueue(new Callback<UsuarioDTO>() {

            @Override
            public void onResponse(Call<UsuarioDTO> call,
                                   Response<UsuarioDTO> response) {

                if(response.isSuccessful() && response.body()!=null){

                    UsuarioDTO u = response.body();

                    sessionManager.guardarSesion(
                            u.getId_usuario(),
                            u.getNombre(),
                            u.getEmail(),
                            chkRecordar.isChecked());

                    startActivity(new Intent(
                            LoginActivity.this,
                            MainActivity.class));

                    finish();

                }else{

                    Toast.makeText(LoginActivity.this,
                            "Correo o contraseña incorrectos",
                            Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<UsuarioDTO> call,
                                  Throwable t) {

                Toast.makeText(LoginActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }

        });

    }

}