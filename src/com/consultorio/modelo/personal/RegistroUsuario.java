package com.consultorio.modelo.personal;

import java.util.Date;

public class RegistroUsuario {

    String id;
    String usuarioAgregado;
    String usuarioResponsable;
    Date fechaRegistro;

    public RegistroUsuario() {}

    public RegistroUsuario(String id, String idUsuarioAgregado, String idUsuarioResponsable, java.sql.Date fechaRegistro) {
        this.id = id;
        this.usuarioAgregado = idUsuarioAgregado;
        this.usuarioResponsable = idUsuarioResponsable;
        this.fechaRegistro = fechaRegistro;

    }

    public String getId() {
        return id;
    }

    public String getUsuarioAgregado() {
        return usuarioAgregado;
    }

    public String getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsuarioAgregado(String usuarioAgregado) {
        this.usuarioAgregado = usuarioAgregado;
    }

    public void setUsuarioResponsable(String usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
