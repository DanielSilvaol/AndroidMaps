package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.R;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.ViewHolder.LocalizacaoViewHolder;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal.Enderecos;

import java.util.List;

public class EnderecosRecyclerViewAdapter extends RecyclerView.Adapter<LocalizacaoViewHolder> {

    private List<Enderecos> chamados;

    public EnderecosRecyclerViewAdapter(List<Enderecos> chamados) {
        this.chamados = chamados;
    }

    @Override
    public int getItemCount() {
        return chamados.size();
    }

    @NonNull
    @Override
    public LocalizacaoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View raiz =
                LayoutInflater.from(viewGroup.getContext()).
                        inflate(R.layout.list_item, viewGroup, false);
        return new LocalizacaoViewHolder(raiz);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalizacaoViewHolder chamadoViewHolder, int i) {
        Enderecos chamadoDaVez = chamados.get(i);
    }
}