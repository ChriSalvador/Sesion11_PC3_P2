package com.example.sesion11_pc3_p2.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sesion11_pc3_p2.enums.ReclamoEnum;
import com.example.sesion11_pc3_p2.model.Reclamo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReclamoDao {

    private final SQLiteDatabase database;
    public ReclamoDao(SQLiteDatabase database) {
        this.database = database;
    }

    public List<Reclamo> getAll() {
        List<Reclamo> reclamos = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", ReclamoEnum.TABLE_NAME.getValue());

        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(ReclamoEnum.COL_ID.getValue());
                int codigoIndex = cursor.getColumnIndex(ReclamoEnum.COL_CODIGO.getValue());
                int asuntoIndex = cursor.getColumnIndex(ReclamoEnum.COL_ASUNTO.getValue());
                int descripcionIndex = cursor.getColumnIndex(ReclamoEnum.COL_DESCRIPCION.getValue());
                int estadoIndex = cursor.getColumnIndex(ReclamoEnum.COL_ESTADO.getValue());
                int fechaCreacionIndex = cursor.getColumnIndex(ReclamoEnum.COL_FECHACREACION.getValue());

                int id = cursor.getInt(idIndex);
                String codigo = cursor.getString(codigoIndex);
                String asunto = cursor.getString(asuntoIndex);
                String descripcion = cursor.getString(descripcionIndex);
                String estado = cursor.getString(estadoIndex);
                String fechaCreacion = cursor.getString(fechaCreacionIndex);

                Reclamo reclamo = new Reclamo(id, codigo, asunto, descripcion, estado, fechaCreacion);
                reclamos.add(reclamo);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return reclamos;
    }

    public long insert(Reclamo reclamo) {
        ContentValues values = new ContentValues();
        values.put(ReclamoEnum.COL_CODIGO.getValue(), reclamo.getCodigo());
        values.put(ReclamoEnum.COL_ASUNTO.getValue(), reclamo.getAsunto());
        values.put(ReclamoEnum.COL_DESCRIPCION.getValue(), reclamo.getDescripcion());
        values.put(ReclamoEnum.COL_ESTADO.getValue(), reclamo.getEstado());
        values.put(ReclamoEnum.COL_FECHACREACION.getValue(), reclamo.getFechaCreacion().toString());

        return database.insert(ReclamoEnum.TABLE_NAME.getValue(), null, values);
    }

    public long update(Reclamo reclamo) {
        ContentValues values = new ContentValues();
        values.put(ReclamoEnum.COL_CODIGO.getValue(), reclamo.getCodigo());
        values.put(ReclamoEnum.COL_ASUNTO.getValue(), reclamo.getAsunto());
        values.put(ReclamoEnum.COL_DESCRIPCION.getValue(), reclamo.getDescripcion());
        values.put(ReclamoEnum.COL_ESTADO.getValue(), reclamo.getEstado());
        values.put(ReclamoEnum.COL_FECHACREACION.getValue(), reclamo.getFechaCreacion().toString());

        String whereClause = String.format("%s=?", ReclamoEnum.COL_ID.getValue());
        String[] whereArgs = {String.valueOf(reclamo.getId())};
        return database.update(ReclamoEnum.TABLE_NAME.getValue(), values, whereClause, whereArgs);
    }

    public long deleteById(String id) {
        String whereClause = String.format("%s=?", ReclamoEnum.COL_ID.getValue());
        String[] whereArgs = {id};

        return database.delete(ReclamoEnum.TABLE_NAME.getValue(), whereClause, whereArgs);
    }

}
