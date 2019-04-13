package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.DBHelper.EnderecosContract;
import com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.DBHelper.EnderecosDBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalizacaoDAO {

    private Context context;
    public LocalizacaoDAO (Context context){
        this.context = context;
    }

    public List<LocalizacaoDAO> busca (String chave){
        EnderecosDBHelper dbHelper = new EnderecosDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List <LocalizacaoDAO> localizacao = new ArrayList<>();

        String sql = String.format(
                "SELECT * FROM %s WHERE %s.%s LIKE '%%%s%%'",
                EnderecosContract.LocalizaContract.TABLE_NAME,
                EnderecosContract.LocalizaContract.TABLE_NAME,
                EnderecosContract.LocalizaContract.COLUMN_NAME_ID,
                EnderecosContract.LocalizaContract.COLUMN_NAME_ID,
                chave
        );
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(
                    String.format(
                            Locale.getDefault(),
                            "%s.%s",
                            EnderecosContract.LocalizaContract.TABLE_NAME,
                            EnderecosContract.LocalizaContract.COLUMN_NAME_ID)
            ));
            Double lat = cursor.getDouble(
                    cursor.getColumnIndex(
                            String.format(
                                    "%s.%s",
                                    EnderecosContract.LocalizaContract.TABLE_NAME,
                                    EnderecosContract.LocalizaContract.COLUMN_NAME_LAT
                            )
                    )
            );
            Double lon = cursor.getDouble(
                    cursor.getColumnIndex(
                            String.format(
                                    Locale.getDefault(),
                                    "%s.%s",
                                    EnderecosContract.LocalizaContract.TABLE_NAME,
                                    EnderecosContract.LocalizaContract.COLUMN_NAME_LON
                            )
                    )
            );


            // localizacao.add(new Localizacao(id, lat, lon));
        }
        cursor.close();
        dbHelper.close();
        db.close();
        return localizacao;
    }
}
