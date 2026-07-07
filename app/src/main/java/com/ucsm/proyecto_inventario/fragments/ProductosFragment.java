package com.ucsm.proyecto_inventario.fragments;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ucsm.proyecto_inventario.R;
import com.ucsm.proyecto_inventario.dto.ProductoDTO;
import com.ucsm.proyecto_inventario.dto.UnidadDTO;
import com.ucsm.proyecto_inventario.repository.ProductoRepository;
import com.ucsm.proyecto_inventario.repository.UnidadRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductosFragment extends Fragment {

    private TextInputEditText etDescripcion;
    private TextInputEditText etCodigo;
    private TextInputEditText etPrecio;
    private TextInputEditText etStock;

    private AutoCompleteTextView spinnerUnidad;

    private MaterialButton btnGuardar;

    private ProductoRepository productoRepository;

    private UnidadRepository unidadRepository;

    private List<UnidadDTO> listaUnidades;

    private int idUnidadSeleccionada;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(
                R.layout.fragment_registrar_producto,
                container,
                false);

        etDescripcion=view.findViewById(R.id.etDescripcion);
        etCodigo=view.findViewById(R.id.etCodigo);
        etPrecio=view.findViewById(R.id.etPrecio);
        etStock=view.findViewById(R.id.etStock);

        spinnerUnidad=view.findViewById(R.id.spinnerUnidad);

        btnGuardar=view.findViewById(R.id.btnGuardarProducto);

        productoRepository=new ProductoRepository();

        unidadRepository=new UnidadRepository();

        cargarUnidades();

        btnGuardar.setOnClickListener(v->guardarProducto());

        view.findViewById(R.id.btnVolver)
                .setOnClickListener(v->
                        Navigation.findNavController(v).popBackStack());

        return view;

    }

    private void cargarUnidades(){

        unidadRepository.listar().enqueue(new Callback<List<UnidadDTO>>() {

            @Override
            public void onResponse(Call<List<UnidadDTO>> call,
                                   Response<List<UnidadDTO>> response) {

                if(response.body()==null)return;

                listaUnidades=response.body();

                List<String> nombres=new ArrayList<>();

                for(UnidadDTO unidad:listaUnidades){

                    nombres.add(unidad.getNombre());

                }

                ArrayAdapter<String> adapter=
                        new ArrayAdapter<>(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                nombres
                        );

                spinnerUnidad.setAdapter(adapter);

                spinnerUnidad.setOnItemClickListener((parent,view,pos,id)->{

                    idUnidadSeleccionada=
                            listaUnidades.get(pos).getId_unidad();

                });

            }

            @Override
            public void onFailure(Call<List<UnidadDTO>> call, Throwable t) {

                Toast.makeText(getContext(),
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }

        });

    }

    private void guardarProducto(){

        ProductoDTO producto=new ProductoDTO();

        producto.setDescripcion(etDescripcion.getText().toString());

        producto.setCodigo(etCodigo.getText().toString());

        producto.setPrecio(
                Double.parseDouble(etPrecio.getText().toString()));

        producto.setStock(
                Integer.parseInt(etStock.getText().toString()));

        producto.setId_unidad(idUnidadSeleccionada);

        productoRepository.registrarProducto(producto)
                .enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call,
                                           Response<Void> response) {

                        Toast.makeText(getContext(),
                                "Producto registrado",
                                Toast.LENGTH_SHORT).show();

                        Navigation.findNavController(requireView())
                                .popBackStack();

                    }

                    @Override
                    public void onFailure(Call<Void> call,
                                          Throwable t) {

                        Toast.makeText(getContext(),
                                t.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }

                });

    }

}