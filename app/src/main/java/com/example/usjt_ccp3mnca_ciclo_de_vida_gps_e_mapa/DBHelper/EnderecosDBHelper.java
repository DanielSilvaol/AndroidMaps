package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EnderecosDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "localizacao.db";
    private static final int DB_VERSION = 1;

    public EnderecosDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EnderecosContract.createTableLocalizacao());
        db.execSQL(EnderecosContract.insertLocalizacao());
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL(EnderecosContract.LocalizaContract.DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
