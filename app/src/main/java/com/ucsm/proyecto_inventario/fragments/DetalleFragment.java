package com.ucsm.proyecto_inventario.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.ucsm.proyecto_inventario.R;
import com.ucsm.proyecto_inventario.dto.ProductoDTO;
import com.ucsm.proyecto_inventario.repository.ProductoRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleFragment extends Fragment {

    private TextView tvDescripcion;
    private TextView tvCodigo;
    private TextView tvPrecio;
    private TextView tvStock;
    private TextView tvUnidad;

    private MaterialButton btnEditar;
    private MaterialButton btnStock;

    private ProductoRepository repository;

    private int idProducto;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_detalle_producto,
                container,
                false);

        tvDescripcion=view.findViewById(R.id.tvDescripcion);
        tvCodigo=view.findViewById(R.id.tvCodigo);
        tvPrecio=view.findViewById(R.id.tvPrecio);
        tvStock=view.findViewById(R.id.tvStock);
        tvUnidad=view.findViewById(R.id.tvUnidad);

        btnEditar=view.findViewById(R.id.btnEditarProducto);
        btnStock=view.findViewById(R.id.btnAjustarStock);

        repository=new ProductoRepository();

        idProducto=getArguments().getInt("idProducto");

        cargarProducto();

        btnEditar.setOnClickListener(v->{

            Bundle bundle=new Bundle();
            bundle.putInt("idProducto",idProducto);

            Navigation.findNavController(v)
                    .navigate(R.id.action_detalleFragment_to_productosFragment,bundle);

        });

        btnStock.setOnClickListener(v->{

            Bundle bundle=new Bundle();
            bundle.putInt("idProducto",idProducto);

            Navigation.findNavController(v)
                    .navigate(R.id.action_detalleFragment_to_ajustarStockFragment,bundle);

        });

        view.findViewById(R.id.btnVolver)
                .setOnClickListener(v->
                        Navigation.findNavController(v).popBackStack());

        return view;
    }

    private void cargarProducto(){

        repository.obtenerProducto(idProducto)
                .enqueue(new Callback<ProductoDTO>() {

                    @Override
                    public void onResponse(Call<ProductoDTO> call,
                                           Response<ProductoDTO> response) {

                        if(response.body()==null)return;

                        ProductoDTO p=response.body();

                        tvDescripcion.setText(p.getDescripcion());
                        tvCodigo.setText(p.getCodigo());
                        tvPrecio.setText("S/. "+p.getPrecio());
                        tvStock.setText(String.valueOf(p.getStock()));
                        tvUnidad.setText(String.valueOf(p.getId_unidad()));

                    }

                    @Override
                    public void onFailure(Call<ProductoDTO> call,
                                          Throwable t) {

                    }

                });

    }

}