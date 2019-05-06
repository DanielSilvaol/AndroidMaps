package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Adapters.EnderecosRecyclerViewAdapter;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.R;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal.Enderecos;

import java.util.ArrayList;

public class ListaEnderecosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_enderecos);
        RecyclerView localizaoRecyclerView = findViewById(R.id.localizacoesRecyclerView);
        localizaoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent origemIntent = getIntent();
        final ArrayList<Enderecos> localizacoes = (ArrayList<Enderecos>) origemIntent.getSerializableExtra("localizacoes");
        localizaoRecyclerView = findViewById(R.id.localizacoesRecyclerView);
        localizaoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EnderecosRecyclerViewAdapter  adapter = new EnderecosRecyclerViewAdapter(localizacoes);
        localizaoRecyclerView.setAdapter(adapter);
    }
}