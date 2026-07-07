package com.ucsm.proyecto_inventario.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.ucsm.proyecto_inventario.R;
import com.ucsm.proyecto_inventario.dto.ProductoDTO;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<ProductoDTO> listaProductos;
    private OnProductoListener listener;

    public ProductoAdapter(List<ProductoDTO> listaProductos, OnProductoListener listener) {
        this.listaProductos = listaProductos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inventario_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductoDTO producto = listaProductos.get(position);

        holder.tvNombreProducto.setText(producto.getDescripcion());

        holder.tvUnidadMedida.setText(producto.getUnidad());

        holder.tvPrecioProducto.setText("S/. " + producto.getPrecio());

        holder.tvStockProducto.setText(producto.getStock() + " unidades");

        holder.btnVerProducto.setOnClickListener(v -> {
            if (listener != null) {
                listener.onVer(producto);
            }
        });

        holder.btnEditarProducto.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditar(producto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos != null ? listaProductos.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreProducto;
        TextView tvUnidadMedida;
        TextView tvPrecioProducto;
        TextView tvStockProducto;

        MaterialButton btnVerProducto;
        MaterialButton btnEditarProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvUnidadMedida = itemView.findViewById(R.id.tvUnidadMedida);
            tvPrecioProducto = itemView.findViewById(R.id.tvPrecioProducto);
            tvStockProducto = itemView.findViewById(R.id.tvStockProducto);

            btnVerProducto = itemView.findViewById(R.id.btnVerProducto);
            btnEditarProducto = itemView.findViewById(R.id.btnEditarProducto);
        }
    }

    public interface OnProductoListener {
        void onVer(ProductoDTO producto);
        void onEditar(ProductoDTO producto);
    }
}