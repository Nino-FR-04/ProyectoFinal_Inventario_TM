package com.ucsm.proyecto_inventario.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.ucsm.proyecto_inventario.R;
import com.ucsm.proyecto_inventario.activities.LoginActivity;
import com.ucsm.proyecto_inventario.utils.SessionManager;

public class MasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){

        View view=inflater.inflate(
                R.layout.fragment_mas,
                container,
                false);

        SessionManager session=new SessionManager(requireContext());

        TextView tvNombre=view.findViewById(R.id.tvNombreUsuario);
        TextView tvCorreo=view.findViewById(R.id.tvEmailUsuario);
        TextView tvRol=view.findViewById(R.id.tvRolUsuario);

        MaterialButton btnCerrar=view.findViewById(R.id.btnCerrarSesion);

        tvNombre.setText(session.getNombre());

        tvCorreo.setText(session.getCorreo());

        tvRol.setText("Administrador");

        btnCerrar.setOnClickListener(v->{

            session.cerrarSesion();

            Intent intent=new Intent(
                    requireActivity(),
                    LoginActivity.class);

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);

        });

        return view;

    }

}