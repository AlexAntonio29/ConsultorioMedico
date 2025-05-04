package com.consultorio.modelo.estructura;

import java.util.Date;

public class RegistroConsultorio {
    private String id;
    private String idUsuario;
    private String nConsultorio;
    private Date fechaRegistro;

    // Constructor vacío
    public RegistroConsultorio() {

    }

    // Constructor con parámetros
    public RegistroConsultorio(String id, String idUsuario, String nConsultorio, Date fechaRegistro) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nConsultorio = nConsultorio;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNConsultorio() {
        return nConsultorio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNConsultorio(String nConsultorio) {
        this.nConsultorio = nConsultorio;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
