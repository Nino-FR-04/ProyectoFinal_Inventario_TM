package com.ucsm.proyecto_inventario.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ucsm.proyecto_inventario.R;
import com.ucsm.proyecto_inventario.adapters.ProductoAdapter;
import com.ucsm.proyecto_inventario.dto.ProductoDTO;
import com.ucsm.proyecto_inventario.repository.ProductoRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventarioFragment extends Fragment {

    private RecyclerView recyclerInventario;
    private FloatingActionButton btnAgregarProducto;

    private ProductoRepository repository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_inventario,
                container,
                false);

        recyclerInventario = view.findViewById(R.id.recyclerInventario);

        btnAgregarProducto = view.findViewById(R.id.btnAgregarProducto);

        recyclerInventario.setLayoutManager(
                new LinearLayoutManager(getContext()));

        repository = new ProductoRepository();

        cargarProductos();

        btnAgregarProducto.setOnClickListener(v->

                Navigation.findNavController(v)
                        .navigate(R.id.action_inventarioFragment_to_productosFragment)

        );

        return view;

    }

    private void cargarProductos(){

        repository.listarProductos().enqueue(new Callback<List<ProductoDTO>>() {

            @Override
            public void onResponse(Call<List<ProductoDTO>> call,
                                   Response<List<ProductoDTO>> response) {

                if(response.isSuccessful() && response.body()!=null){

                    ProductoAdapter adapter =
                            new ProductoAdapter(
                                    response.body(),
                                    listener);

                    recyclerInventario.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<ProductoDTO>> call,
                                  Throwable t) {

                Toast.makeText(
                        getContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();

            }

        });

    }

    private final ProductoAdapter.OnProductoListener listener =
            new ProductoAdapter.OnProductoListener() {

                @Override
                public void onVer(ProductoDTO producto) {

                    Bundle bundle = new Bundle();
                    bundle.putInt("idProducto", producto.getId_producto());

                    Navigation.findNavController(requireView())
                            .navigate(R.id.action_inventarioFragment_to_detalleFragment, bundle);

                }

                @Override
                public void onEditar(ProductoDTO producto) {

                    Bundle bundle = new Bundle();
                    bundle.putInt("idProducto", producto.getId_producto());

                    Navigation.findNavController(requireView())
                            .navigate(R.id.action_inventarioFragment_to_productosFragment, bundle);

                }

            };

}