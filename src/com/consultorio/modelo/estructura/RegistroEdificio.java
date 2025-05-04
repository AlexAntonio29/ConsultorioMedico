package com.consultorio.modelo.estructura;



import java.util.Date;

public class RegistroEdificio {
    private int id;
    private int idUsuario;
    private int idEdificio;
    private Date fechaRegistro;

    // 🔹 Constructor por defecto
    public RegistroEdificio() {}

    // 🔹 Constructor con parámetros
    public RegistroEdificio(int id, int idUsuario, int idEdificio, Date fechaRegistro) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEdificio = idEdificio;
        this.fechaRegistro = fechaRegistro;
    }

    // 🔹 Métodos getters
    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    // 🔹 Métodos setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}

