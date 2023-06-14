package com.example.sesion11_pc3_p2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sesion11_pc3_p2.enums.ReclamoEnum;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Atencion.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper instance;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryReclamo =String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER " +
                        "PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s DATE);",
                ReclamoEnum.TABLE_NAME.getValue(),
                ReclamoEnum.COL_ID.getValue(),
                ReclamoEnum.COL_CODIGO.getValue(),
                ReclamoEnum.COL_ASUNTO.getValue(),
                ReclamoEnum.COL_DESCRIPCION.getValue(),
                ReclamoEnum.COL_ESTADO.getValue(),
                ReclamoEnum.COL_FECHACREACION.getValue()
        );
        sqLiteDatabase.execSQL(queryReclamo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
