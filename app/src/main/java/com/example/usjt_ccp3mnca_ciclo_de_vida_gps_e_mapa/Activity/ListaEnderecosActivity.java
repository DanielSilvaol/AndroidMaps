package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Adapters.EnderecosRecyclerViewAdapter;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.R;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal.Enderecos;

import java.util.ArrayList;

public class ListaEnderecosActivity extends AppCompatActivity {

    private RecyclerView locationsRecyclerView;
    private static Activity instancia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instancia = this;
        setContentView(R.layout.activity_lista_enderecos);
        locationsRecyclerView = findViewById(R.id.localizacoesRecyclerView);
        locationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent origemIntent = getIntent();
        final ArrayList<Enderecos> localizacoes = (ArrayList<Enderecos>) origemIntent.getSerializableExtra("lista");
        EnderecosRecyclerViewAdapter adapter = new EnderecosRecyclerViewAdapter(localizacoes);
        locationsRecyclerView.setAdapter(adapter);

//        locationsRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
//
//                Enderecos enderecos01 = enderecos.get(posicao);
//                String latAux = String.valueOf(enderecos01.getLatetude()).replace(',',',');
//                String longAux =String.valueOf(enderecos01.getLongetude()).replace(',','.');
//                @SuppressLint("DefaultLocale") Uri uri = Uri.parse(String.format("geo:%f,%f?q=%s,%s",enderecos01.getLatetude(),enderecos01.getLongetude(),latAux,longAux));
//
//                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                intent.setPackage("com.google.android.apps.maps");
////                startActivityForResult(intent,1);
//                startActivity(intent);
//            }
//        });
    }
}
