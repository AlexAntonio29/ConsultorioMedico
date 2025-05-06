package com.consultorio.modelo.estructura;

public class Consultorio {

    String nConsultorio;
    String especialidad;
    int disponibilidad;
    Edificio edificio;
    String numeroPiso;

    public Consultorio(){}
    public Consultorio(String nConsultorio, String especialidad, int disponibilidad, Edificio edificio, String numeroPiso) {
        this.nConsultorio = nConsultorio;
        this.especialidad = especialidad;
        this.disponibilidad = disponibilidad;
        this.edificio = edificio;
        this.numeroPiso = numeroPiso;
    }


    public String getNConsultorio() {
        return nConsultorio;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public String getNumeroPiso() {
        return numeroPiso;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setNConsultorio(String nConsultorio) {
        this.nConsultorio = nConsultorio;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setNumeroPiso(String numeroPiso) {
        this.numeroPiso = numeroPiso;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }


}
