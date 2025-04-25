package com.consultorio.modelo.clientes;

public class RegistroPaciente {

    private int id;
    private int idUsuario;
    private int idPaciente;
    private java.util.Date fechaRegistro;

    // 🔹 Constructor vacío
    public RegistroPaciente() {}

    // 🔹 Constructor con parámetros
    public RegistroPaciente(int id, int idUsuario, int idPaciente, java.util.Date fechaRegistro) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPaciente = idPaciente;
        this.fechaRegistro = fechaRegistro;
    }

    // 🔹 Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public java.util.Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(java.util.Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    // 🔹 Método para imprimir detalles del registro

}
