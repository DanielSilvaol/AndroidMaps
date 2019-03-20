package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaEnderecosActivity extends AppCompatActivity {

    private ListView chamadosListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_enderecos);
        chamadosListView = findViewById(R.id.chamadosLisView);
        Intent origemIntent = getIntent();
        final List<String> chamados = origemIntent.getStringArrayListExtra("lista");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,chamados);
        chamadosListView.setAdapter(adapter);
        chamadosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
            String local = chamados.get(posicao);
            String[] localizacaoAtual = local.split(",");

                double lat = Double.parseDouble(localizacaoAtual[0]);
                double lon = Double.parseDouble(localizacaoAtual[1]);
                String latAux = String.valueOf(lat).replace(',',',');
                String longAux =String.valueOf(lon).replace(',','.');
                @SuppressLint("DefaultLocale") Uri uri = Uri.parse(String.format("geo:%f,%f?q=%s,%s",lat,lon,latAux,longAux));
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android.apps.maps");
                startActivityForResult(intent,1);

            }
        });
    }
}
