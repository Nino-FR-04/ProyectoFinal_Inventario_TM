package com.ucsm.proyecto_inventario.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ucsm.proyecto_inventario.R;
import com.ucsm.proyecto_inventario.dto.DashboardDTO;
import com.ucsm.proyecto_inventario.repository.DashboardRepository;
import com.ucsm.proyecto_inventario.utils.SessionManager;

import retrofit2.*;

public class HomeFragment extends Fragment {

    private TextView tvSaludo;
    private TextView tvTotalProductos;
    private TextView tvStockBajo;
    private TextView tvSinStock;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){

        View view=inflater.inflate(
                R.layout.fragment_home,
                container,
                false);

        tvSaludo=view.findViewById(R.id.tvSaludo);
        tvTotalProductos=view.findViewById(R.id.tvTotalProductos);
        tvStockBajo=view.findViewById(R.id.tvStockBajo);
        tvSinStock=view.findViewById(R.id.tvSinStock);

        SessionManager session=new SessionManager(requireContext());

        tvSaludo.setText("Hola, "+session.getNombre());

        DashboardRepository repository=new DashboardRepository();

        repository.obtenerDashboard().enqueue(new Callback<DashboardDTO>() {

            @Override
            public void onResponse(Call<DashboardDTO> call,
                                   Response<DashboardDTO> response) {

                if(response.body()==null)return;

                DashboardDTO dashboard=response.body();

                tvTotalProductos.setText(
                        String.valueOf(dashboard.getTotalProductos()));

                tvStockBajo.setText(
                        String.valueOf(dashboard.getStockBajo()));

                tvSinStock.setText(
                        String.valueOf(dashboard.getSinStock()));

            }

            @Override
            public void onFailure(Call<DashboardDTO> call,
                                  Throwable t) {

            }

        });

        return view;

    }

}