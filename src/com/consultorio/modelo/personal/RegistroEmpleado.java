package com.consultorio.modelo.personal;

import java.util.Date;

public class RegistroEmpleado {
    private String id;
    private String idUsuario;
    private String idEmpleado;
    private Date fechaRegistro;

    // Constructor vacío
    public RegistroEmpleado() {}

    // Constructor con parámetros
    public RegistroEmpleado(String id, String idUsuario, String idEmpleado, Date fechaRegistro) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEmpleado = idEmpleado;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getIdEmpleado() {
        return idEmpleado;
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

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
