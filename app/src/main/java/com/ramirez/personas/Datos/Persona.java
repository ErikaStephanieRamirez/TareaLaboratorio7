package com.ramirez.personas.Datos;

/**
 * Created by Erika on 16/5/2018.
 */

public class Persona {
    private String carnet;
    private String nombre;
    private String nota;


    public Persona(){

    }

    public Persona(String carnet, String nombre, String nota) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.nota = nota;
    }

    public Persona(String carnet, String nombre) {
        this.carnet = carnet;
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
