package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.R;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.dao.LocalizacaoDAO;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal.Enderecos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LocalizacaoDAO localizacaoDAO;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int REQUEST_CODE_GPS = 1001;
    private TextView textView;
    private Location localizacao;

    private ArrayList<Enderecos> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        localizacaoDAO = new LocalizacaoDAO(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaEnderecosActivity.class);
                intent.putExtra("lista", list);
                startActivity(intent);
            }
        });


        textView = findViewById(R.id.LOCALIZACAO_TEXT);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onLocationChanged(Location location) {


                double lat = location.getLatitude();
                double log = location.getLongitude();
                Enderecos enderecos = new Enderecos(log,lat);
                textView.setText(String.format("Lat: %.2f, Long: %.2f", lat, log));
                if (localizacao == null){
                    localizacao = location;
                    list.add(enderecos);
                }
                    double var = getDistanceBetween(localizacao, location);

                if (var > 200 && list.size() <= 50) {
                    localizacao.setLatitude(lat);
                    localizacao.setLongitude(log);
                    list.add(enderecos);
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_GPS);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GPS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    Toast.makeText(this, getString(R.string.yes_gps_yes_app), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, getString(R.string.no_gps_no_app), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static Double getDistanceBetween(Location latLon1, Location latLon2) {
        if (latLon1 == null || latLon2 == null)
            return null;
        float[] result = new float[1];
        Location.distanceBetween(latLon1.getLatitude(), latLon1.getLongitude(), latLon2.getLatitude(), latLon2.getLongitude(), result);
        return (double) result[0];
    }
}
