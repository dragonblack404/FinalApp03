package com.example.finalapp03.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_BD = "armamento.db";
    public static final int VERSION_BD = 1;
    public static final String ARTICULOS = "articulos";

    public DBHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ ARTICULOS+ "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT NOT NULL,"
                + "precio REAL NOT NULL,"
                + "descripcion TEXT NOT NULL"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ARTICULOS);
        onCreate(db);
    }

//    public SQLiteDatabase getDataBase(){
//        return
//    }
}
