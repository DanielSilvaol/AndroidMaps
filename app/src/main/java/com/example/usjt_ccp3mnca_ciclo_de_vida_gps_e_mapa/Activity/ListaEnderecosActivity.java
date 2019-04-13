package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Adapters.EnderecosRecyclerViewAdapter;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.R;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.dao.LocalizacaoDAO;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal.Enderecos;

import java.util.ArrayList;

public class ListaEnderecosActivity extends AppCompatActivity {
    private RecyclerView localizaoRecyclerView;
    private LocalizacaoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dao = LocalizacaoDAO(localizaoRecyclerView);
        setContentView(R.layout.activity_lista_enderecos);
        localizaoRecyclerView = findViewById(R.id.localizacoesRecyclerView);
        localizaoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent origemIntent = getIntent();
        final ArrayList<Enderecos> localizacoes = (ArrayList<Enderecos>) origemIntent.getSerializableExtra("lista");
        localizaoRecyclerView = findViewById(R.id.localizacoesRecyclerView);
        localizaoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EnderecosRecyclerViewAdapter adapter = new EnderecosRecyclerViewAdapter(localizacoes);
        localizaoRecyclerView.setAdapter(adapter);
    }

}