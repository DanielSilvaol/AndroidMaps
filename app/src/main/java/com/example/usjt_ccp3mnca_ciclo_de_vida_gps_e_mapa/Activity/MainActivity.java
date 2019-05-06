package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.Adapters.WeatherAdapter;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.R;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal.Enderecos;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LocationListener locationListener;
    private LocationManager locationManager;
    private static final int REQUEST_CODE_GPS = 1001;
    private TextView lblLocalizacao;

    private RecyclerView weatherRecyclerView;
    private WeatherAdapter adapter;
    private List<Weather> previsoes;
    private TextInputEditText locationEditText;
    private RequestQueue requestQueue;

    private double latitudeAtual;
    private double longitudeAtual;
    private ArrayList<Enderecos> localizacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        localizacoes = new ArrayList<>();
        weatherRecyclerView =
                findViewById(R.id.weatherRecyclerView);
        previsoes = new ArrayList<>();
        adapter = new WeatherAdapter(previsoes, this);
        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        weatherRecyclerView.setAdapter(adapter);
        requestQueue = Volley.newRequestQueue(this);

        lblLocalizacao = findViewById(R.id.lblLocalizacao);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onLocationChanged(Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                Enderecos localizacao = new Enderecos(lat, lon);
                localizacao.setLatetude(latitudeAtual);
                localizacao.setLongetude(longitudeAtual);
                latitudeAtual = lat;
                longitudeAtual = lon;
                localizacoes.add(localizacao);
                if (localizacoes.size() > 50) {
                    Log.e("Size", ">50");
                    localizacoes.remove(0);
                }
                FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        obtemPrevisoesV5(lat,lon);
                    }
                });
                lblLocalizacao.setText(String.format("Lat: %f, Long: %f", lat, lon));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };




    }

    @Override
    protected void onStart() {
        super.onStart();

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,
                    200, locationListener);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_GPS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GPS) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 200, locationListener);
                }
            } else {
                Toast.makeText(this, getString(R.string.no_gps_no_app), Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    public void obtemPrevisoesV5(Double lat, Double lon) {
        @SuppressLint("StringFormatMatches") String url = getString(
                R.string.web_service_url,
                lat,
                lon,
                getString(R.string.api_key)
        );
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                (response) -> {
                    previsoes.clear();
                    try {
                        JSONArray list = response.getJSONArray("list");
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject day = list.getJSONObject(i);
                            JSONObject main = day.getJSONObject("main");
                            JSONObject weather = day.getJSONArray("weather").getJSONObject(0);
                            previsoes.add(new Weather(day.getLong("dt"), main.getDouble("temp_min"),
                                    main.getDouble("temp_max"), main.getDouble("humidity"),
                                    weather.getString("description"), weather.getString("icon")));
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                (error) -> {
                    Toast.makeText(
                            MainActivity.this,
                            getString(R.string.connect_error) + ": " + error.getLocalizedMessage(),
                            Toast.LENGTH_SHORT
                    ).show();
                }
        );
        requestQueue.add(req);
    }

    class ObtemPrevisoes extends AsyncTask <String, Void, String>{
        @Override
        protected String doInBackground(String... enderecos) {
            try {
                @SuppressLint("StringFormatMatches") String endereco = getString(
                        R.string.web_service_url,
                        enderecos[0],
                        getString(R.string.api_key)
                );
                URL url = new URL(endereco);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder resultado = new StringBuilder("");
                String aux = null;
                while ((aux = reader.readLine()) != null)
                    resultado.append(aux);
                return resultado.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(String resultado) {
            lidaComJSON(resultado);
        }
    }
    public void lidaComJSON (String resultado){
        previsoes.clear();
        try{
            JSONObject json = new JSONObject(resultado);
            JSONArray list = json.getJSONArray("list");
            for (int i = 0; i < list.length(); i++){
                JSONObject day = list.getJSONObject(i);
                JSONObject main = day.getJSONObject("main");
                JSONObject weather = day.getJSONArray("weather").getJSONObject(0);
                previsoes.add (new Weather(day.getLong("dt"), main.getDouble("temp_min"),
                        main.getDouble("temp_max"), main.getDouble ("humidity"),
                        weather.getString("description"),weather.getString("icon")));
            }
            adapter.notifyDataSetChanged();
            dismissKeyboard(weatherRecyclerView);

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }




    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dismissKeyboard (View view){
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
