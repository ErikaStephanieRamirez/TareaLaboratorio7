package com.ramirez.personas.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ramirez.personas.Base.DBHelper;
import com.ramirez.personas.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_registrar, btn_consultar, btn_consultarS, ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarControles();
        DBHelper.getInstance(this);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registar.class);
                startActivity(intent);
            }
        });

        btn_consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Modificar.class);
                startActivity(intent);
            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultarActivity.class);
                startActivity(intent);
            }
        });

    }

    public void inicializarControles(){
        btn_registrar = findViewById(R.id.mostrarRegistro);
        btn_consultar = findViewById(R.id.consultar_usuario);
        ver = findViewById(R.id.verlista);
    }
}
