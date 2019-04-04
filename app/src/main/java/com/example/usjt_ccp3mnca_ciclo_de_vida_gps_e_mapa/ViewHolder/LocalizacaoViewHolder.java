package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.R;

public class LocalizacaoViewHolder extends RecyclerView.ViewHolder {

    public ImageView filaIconImageView;

    public LocalizacaoViewHolder (View v){
        super(v);
        filaIconImageView =
                v.findViewById(R.id.iconLocalizacaoImageView);
    }
}