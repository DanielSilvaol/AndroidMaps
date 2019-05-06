package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.R;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal.Enderecos;

import java.util.ArrayList;

public class EnderacosArrayAdapter extends ArrayAdapter<Enderecos> {

    public EnderacosArrayAdapter(Context context, ArrayList<Enderecos> localizacoes) {
        super(context, -1, localizacoes);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Enderecos enderecos = getItem(position);
        ViewHolder vh = null;
        if (convertView == null) {
            Context context = getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            vh = new ViewHolder();
            vh.filaIconImageView = convertView.findViewById(R.id.icon);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
//        TextView latitude = convertView.findViewById(R.id.latitudeTextView);
//        TextView longitude = convertView.findViewById(R.id.longitudeTextView);

//        latitude.setText("Lat: " + (enderecos != null ? String.format("%.4f", enderecos.getLatetude()) : null));
//        longitude.setText("Long: " + (enderecos != null ? String.format("%.4f", enderecos.getLongetude()) : null));
        return convertView;
    }


    private class ViewHolder {
        public ImageView filaIconImageView;
        public TextView statusChamadoNaFilaTextView;
        public TextView dataAberturaChamadoNaFilaTextView;
        public TextView dataFechamentoChamadoNaFilaTextView;
        public TextView descricaoChamadoNaFilaTextView;
    }

}
