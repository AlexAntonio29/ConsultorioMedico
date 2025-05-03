package com.consultorio.modelo.estructura;

public class Edificio {

    String id;
    String numeroEdificio;
    String nombreEdificio;
    String direccionEdificio;
    String numeroPisos;

    public Edificio(){

    }
    public Edificio(String id, String numeroEdificio, String nombreEdificio, String direccionEdificio, String numeroPisos){
        this.id = id;
        this.numeroEdificio = numeroEdificio;
        this.nombreEdificio = nombreEdificio;
        this.direccionEdificio = direccionEdificio;
        this.numeroPisos = numeroPisos;
    }

    public String getId() {
        return id;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public String getNumeroEdificio() {
        return numeroEdificio;
    }
    public String getDireccionEdificio() {
        return direccionEdificio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public void setNumeroEdificio(String numeroEdificio) {
        this.numeroEdificio = numeroEdificio;
    }

    public void setDireccionEdificio(String direccionEdificio) {
        this.direccionEdificio = direccionEdificio;
    }

    public String getNumeroPisos() {
        return numeroPisos;
    }

    public void setNumeroPisos(String numeroPisos) {
        this.numeroPisos = numeroPisos;
    }
}
