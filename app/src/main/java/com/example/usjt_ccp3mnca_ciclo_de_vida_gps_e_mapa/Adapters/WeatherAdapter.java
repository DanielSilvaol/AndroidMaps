package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.R;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal.Weather;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter
        <WeatherAdapter.WeatherViewHolder> {
    private List<Weather> previsoes;
    private Context context;
    public WeatherAdapter (List<Weather> previsoes, Context context){
        this.previsoes = previsoes;
        this.context = context;
    }
    //viewholder
    class WeatherViewHolder extends RecyclerView.ViewHolder{
        private ImageView coditionImageView;
        private TextView dayTextView;
        private TextView lowTextView;
        private TextView highTextView;
        private TextView humidityTextView;

        public WeatherViewHolder(View raiz) {
            super(raiz);
            coditionImageView = raiz.findViewById(R.id.conditionImageView);
            dayTextView = raiz.findViewById(R.id.dayTextView);
            lowTextView = raiz.findViewById(R.id.lowTextView);
            highTextView = raiz.findViewById(R.id.highTextView);
            humidityTextView = raiz.findViewById(R.id.humidityTextView);

        }
    }
    //quando um viewholder for criado
    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View raiz = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new WeatherViewHolder (raiz);
    }
    //quando um viewholder for vinculado ao recyclerview
    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {
        Weather dadoDeInteresse = previsoes.get(i);
        weatherViewHolder.
                dayTextView.
                setText(
                        context.getString(
                                R.string.day_description,
                                dadoDeInteresse.dayOfWeek,
                                dadoDeInteresse.description
                        )
                );
        weatherViewHolder.
                lowTextView.
                setText(
                        context.getString(
                                R.string.low_temp,
                                dadoDeInteresse.minTemp
                        )
                );
        weatherViewHolder.
                highTextView.
                setText(
                        context.getString(
                                R.string.high_temp,
                                dadoDeInteresse.maxTemp
                        )
                );
        weatherViewHolder.
                humidityTextView.
                setText(
                        context.getString(
                                R.string.humidity,
                                dadoDeInteresse.humidity
                        )
                );
    }
    //qual o total de elementos?
    @Override
    public int getItemCount() {
        return this.previsoes.size();
    }
}
