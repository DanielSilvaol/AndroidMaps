package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.R;

public class LocalizacaoViewHolder extends RecyclerView.ViewHolder {
    public ImageView iconLocalizacaoImageView;
    public TextView latitudeTextView;
    public TextView longitudeTextView;

    public LocalizacaoViewHolder(View itemView) {
        super(itemView);
        iconLocalizacaoImageView = itemView.findViewById(R.id.iconLocalizacaoImageView);
        latitudeTextView = itemView.findViewById(R.id.latitudeTextView);
        longitudeTextView = itemView.findViewById(R.id.longitudeTextView);
    }
}