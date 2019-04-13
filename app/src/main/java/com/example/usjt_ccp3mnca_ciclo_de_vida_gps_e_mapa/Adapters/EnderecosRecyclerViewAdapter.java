package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Adapters;

import android.annotation.SuppressLint;
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

    private List<Enderecos> localizacoes;

    public EnderecosRecyclerViewAdapter(List<Enderecos> localizacoes){
        this.localizacoes = localizacoes;
    }

    @NonNull
    @Override
    public LocalizacaoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View raiz = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new LocalizacaoViewHolder(raiz);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LocalizacaoViewHolder locaisViewHolder, int i) {
        Enderecos localizacao = localizacoes.get(i);
        locaisViewHolder.iconLocalizacaoImageView.setImageResource(R.drawable.ic_location_on_black_24dp);
        locaisViewHolder.longitudeTextView.setText("Longitude: " + localizacao.getLongetude());
        locaisViewHolder.latitudeTextView.setText("Latitude: " + localizacao.getLatetude());
    }

    @Override
    public int getItemCount() {
        return localizacoes.size();
    }
}