package com.ramirez.personas.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ramirez.personas.Adapter.Adapter;
import com.ramirez.personas.Base.DBHelper;
import com.ramirez.personas.Datos.Persona;
import com.ramirez.personas.R;

import java.util.ArrayList;

public class ConsultarActivity extends AppCompatActivity {

    RecyclerView rv;
    Adapter adapter;
    TextView promedio;
    ArrayList<Persona> estudiante;
    LinearLayoutManager lManager;
    String respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        promedio= findViewById(R.id.prom);
        String ans= ""+DBHelper.myDB.promedio();
        promedio.setText(ans);

        rv= findViewById(R.id.recycler);

        estudiante= new ArrayList<>();

        lManager= new LinearLayoutManager(this);

        rv.setLayoutManager(lManager);
        estudiante = DBHelper.myDB.prepareDatos();

        adapter= new Adapter(estudiante) {
            @Override
            public void onVerClick(View v, int pos) {

            }

            @Override
            public void Contador(int cont) {

            }
        };

        rv.setAdapter(adapter);

        respuesta= ""+DBHelper.myDB.promedio();
        promedio.setText(ans);
    }
}
