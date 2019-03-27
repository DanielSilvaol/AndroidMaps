package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EnderacosArrayAdapter extends ArrayAdapter<Enderecos> {

    public EnderacosArrayAdapter(Context context, ArrayList<Enderecos> enderecos) {
        super(context, -1, enderecos);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Enderecos enderecos = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView latitude = convertView.findViewById(R.id.latitudeTextView);
        TextView longitude = convertView.findViewById(R.id.longitudeTextView);

        latitude.setText("Lat: " + (enderecos != null ? String.format("%.4f",enderecos.getLatetude()) : null));
        longitude.setText("Long: " + (enderecos != null ? String.format("%.4f",enderecos.getLongetude()) : null));
        return convertView;
    }
}
