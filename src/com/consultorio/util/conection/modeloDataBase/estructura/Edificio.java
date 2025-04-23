package com.consultorio.util.conection.modeloDataBase.estructura;

public class Edificio {

    String id;
    String numeroEdificio;
    String nombreEdificio;
    String direccionEdificio;

    public Edificio(){

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


}
