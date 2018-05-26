package com.ramirez.personas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ramirez.personas.Datos.Persona;
import com.ramirez.personas.Entidades.DBHelper;

public class Modificar extends AppCompatActivity {

    private EditText id,nombre,nota;
    private Button btnBuscar,btnEliminar, btnActualizar,btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        inicializarControles();

        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Persona p = DBHelper.myDB.findUser(id.getText().toString());

                if(p==null){
                    Toast.makeText(getApplicationContext(),"El usuario no fue encontrado", Toast.LENGTH_SHORT).show();
                    limpiar();
                }
                else{
                    nombre.setText(p.getNombre());
                    nota.setText(p.getNota());
                }
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DBHelper.myDB.editUser(new Persona(id.getText().toString(),
                                        nota.getText().toString()));
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DBHelper.myDB.deleteUser(id.getText().toString());
                limpiar();
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                limpiar();
            }
        });
    }

    public void inicializarControles(){
        id=findViewById(R.id.txtIdM);
        nombre=findViewById(R.id.txtNombreN);
        nota=findViewById(R.id.txtNotaN);
        btnBuscar=findViewById(R.id.btnBuscarM);
        btnActualizar=findViewById(R.id.btnActualizarM);
        btnEliminar=findViewById(R.id.btnEliminarM);
        btnLimpiar=findViewById(R.id.btnLimpiarM);
    }

    public void limpiar(){
        nombre.setText("");
        id.setText("");
    }
}
