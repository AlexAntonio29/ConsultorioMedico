package com.consultorio.modelo.movimiento.movimientoMedico;



import java.util.Date;

public class RegistroCita {
    private String id;
    private String idUsuario;
    private String idCita;
    private Date fechaRegistro;

    public RegistroCita(){}

    public RegistroCita(String id, String idUsuario, String idCita, Date fechaRegistro) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCita = idCita;
        this.fechaRegistro = fechaRegistro;
    }

    // 📌 Getters
    public String getId() { return id; }
    public String getIdUsuario() { return idUsuario; }
    public String getIdCita() { return idCita; }
    public Date getFechaRegistro() { return fechaRegistro; }

    // 📌 Setters
    public void setId(String id) { this.id = id; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    public void setIdCita(String idCita) { this.idCita = idCita; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
