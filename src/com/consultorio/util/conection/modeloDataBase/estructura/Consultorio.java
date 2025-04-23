package com.consultorio.util.conection.modeloDataBase.estructura;

public class Consultorio {

    String nConsultorio;
    String especialidad;
    boolean disponibilidad;
    String edificio;
    String piso;



    public String getnConsultorio() {
        return nConsultorio;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public String getPiso() {
        return piso;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setnConsultorio(String nConsultorio) {
        this.nConsultorio = nConsultorio;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }


}
