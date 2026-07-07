package com.ucsm.proyecto_inventario.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ucsm.proyecto_inventario.R;
import com.ucsm.proyecto_inventario.dto.MovimientoDTO;
import com.ucsm.proyecto_inventario.repository.ProductoRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjustarStockFragment extends Fragment {

    private TextInputEditText masCantidadStock;
    private TextInputEditText menosCantidadStock;

    private MaterialButton btnAplicarCambios;

    private ProductoRepository repository;

    private int idProducto;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(
                R.layout.fragment_ajustar_stock,
                container,
                false);

        repository=new ProductoRepository();

        idProducto=getArguments().getInt("idProducto");

        masCantidadStock=view.findViewById(R.id.masCantidadStock);

        menosCantidadStock=view.findViewById(R.id.menosCantidadStock);

        btnAplicarCambios=view.findViewById(R.id.btnAplicarCambios);

        btnAplicarCambios.setOnClickListener(v->actualizar());

        view.findViewById(R.id.btnVolver)
                .setOnClickListener(v->
                        Navigation.findNavController(v).popBackStack());

        return view;

    }

    private void actualizar(){

        int cantidad=0;

        String tipo="";

        if(!masCantidadStock.getText().toString().isEmpty()){

            cantidad=Integer.parseInt(
                    masCantidadStock.getText().toString());

            tipo="ENTRADA";

        }

        if(!menosCantidadStock.getText().toString().isEmpty()){

            cantidad=Integer.parseInt(
                    menosCantidadStock.getText().toString());

            tipo="SALIDA";

        }

        MovimientoDTO movimiento=
                new MovimientoDTO(cantidad,tipo);

        repository.actualizarStock(idProducto,movimiento)
                .enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call,
                                           Response<Void> response) {

                        Toast.makeText(getContext(),
                                "Stock actualizado",
                                Toast.LENGTH_SHORT).show();

                        Navigation.findNavController(requireView())
                                .popBackStack();

                    }

                    @Override
                    public void onFailure(Call<Void> call,
                                          Throwable t) {

                    }

                });

    }

}