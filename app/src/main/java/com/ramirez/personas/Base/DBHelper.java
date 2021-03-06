package com.ramirez.personas.Base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.ramirez.personas.Datos.Persona;

import java.util.ArrayList;

/**
 * Created by Erika on 16/5/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_NOMBRE);
        onCreate(db);
    }

    public static final String DB_NAME="bd_usuarios";
    public static final String TABLA_USUARIO="Alumno";
    public static final String CAMPO_ID="carnet";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_NOTA = "nota";
    public static final String PROMEDIO = "SELECT AVG("+CAMPO_NOTA +") FROM "+TABLA_USUARIO;
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO
                                                    + "(" + CAMPO_ID + " TEXT," + CAMPO_NOMBRE + " TEXT, " + CAMPO_NOTA + " TEXT)";


    public static DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    public static DBHelper getInstance(Context ctx){
        if(myDB == null){
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;
    }

    public DBHelper(Context context){
        super(context,DB_NAME,null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }

    public boolean add(Persona p){
        ContentValues values = new ContentValues();
        values.put(CAMPO_ID, p.getCarnet());
        values.put(CAMPO_NOMBRE, p.getNombre());
        values.put(CAMPO_NOTA, "0.0");

        db.insert(TABLA_USUARIO, null, values);
        Toast.makeText(context, "Alumno insertado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public Persona findUser(String carnet) {
        Persona p;
        String[] parametros = {carnet};
        String[] campos = {CAMPO_NOMBRE};
        String[] campos2 = {CAMPO_NOTA};

        try {
            Cursor cursor = db.query(TABLA_USUARIO, campos,
                    CAMPO_ID + "=?", parametros,
                    null, null, null);
            cursor.moveToFirst();

            Cursor cursorn = db.query(TABLA_USUARIO, campos2, CAMPO_ID + "=?", parametros, null, null, null);
            cursorn.moveToFirst();

            p = new Persona(carnet, cursor.getString(0), cursorn.getString(0));
            cursor.close();
            cursorn.close();

        } catch (Exception e) {
            p = null;
        }
        return p;
    }

    public boolean editUser(Persona p){
        String [] parametros = {p.getCarnet()};
        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();
        values.put(CAMPO_NOMBRE,p.getNombre());
        values2.put(CAMPO_NOTA, p.getNota());
        db.update(TABLA_USUARIO,values,CAMPO_ID+"=?",parametros);
        db.update(TABLA_USUARIO, values2, CAMPO_ID + "=?", parametros);
        Toast.makeText(context,"Alumno Actualizado con exito",Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean deleteUser(String carnet) {
        String[] parametros = {carnet};
        db.delete(TABLA_USUARIO, CAMPO_ID + "=?", parametros);
        Toast.makeText(context, "Alumno Eliminado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public ArrayList<Persona> prepareDatos(){

        ArrayList<Persona> lista = new ArrayList<Persona>();
        String selectQuery = "SELECT  * FROM " + TABLA_USUARIO;

        Cursor info = db.rawQuery(selectQuery, null);
        try {
            if (info.moveToFirst()) {
                do {
                    Persona informacion = new Persona();
                    informacion.setCarnet(info.getString(0));
                    informacion.setNombre(info.getString(1));
                    informacion.setNota(info.getString(2));
                    lista.add(informacion);
                } while (info.moveToNext());
            }
        }
        finally {
            try {
                info.close();
            } catch (Exception ignore) { }
        }
        return lista;
    }

    public float promedio() {
        float prom;
        Cursor promedio = db.rawQuery(PROMEDIO, null);
        promedio.moveToFirst();
        prom =(promedio.getFloat(0));
        promedio.close();

        return prom;
    }
}
