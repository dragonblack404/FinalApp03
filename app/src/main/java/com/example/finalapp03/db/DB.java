package com.example.finalapp03.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.finalapp03.model.Articulo;

public class DB extends DBHelper{


    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;

    public DB(@Nullable Context context){
        super(context);
        this.context = context;
    }

//    public DB(@Nullable Context context) {
//        super(context);
//        dbHelper = new DBHelper(context);
//    }
//
//    public void open() throws SQLException {
//        db = dbHelper.getWritableDatabase();
//    }
//
//    public void close() {
//        dbHelper.close();
//    }

    public long insertarProducto(String nombre, double precio, String descripcion) {
        long id = 0;

        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("precio", precio);
            values.put("descripcion", descripcion);

            id = db.insert(ARTICULOS, null, values);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return id;
    }

    public int actualizarProducto(long id, String nombre, double precio, String descripcion) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("precio", precio);
        values.put("descripcion", descripcion);
        return db.update("productos", values, "id=?", new String[]{Long.toString(id)});
    }

    public int borrarProducto(long id) {
        return db.delete("productos", "id=?", new String[]{Long.toString(id)});
    }

    public Articulo getArticulo(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Articulo articulo = null;
        Cursor cursorArticulos;

        cursorArticulos = db.rawQuery("SELECT * FROM " + ARTICULOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorArticulos.moveToFirst()) {
            articulo = new Articulo();
            articulo.setId(cursorArticulos.getInt(0));
            articulo.setNombre(cursorArticulos.getString(1));
            articulo.setPrecio(cursorArticulos.getString(2));
            articulo.setDescripcion(cursorArticulos.getString(3));
        }

        cursorArticulos.close();

        return articulo;
    }
}