package com.consultorio.modelo.movimiento.movimientoMedico;

import com.consultorio.modelo.estructura.Consultorio;
import com.consultorio.modelo.clientes.Paciente;
import com.consultorio.modelo.personal.Usuario;

import java.time.LocalTime;
import java.util.Date;

public class Cita {
    String id;
    Usuario usuario;
    Paciente paciente;
    Date fecha;
    LocalTime hora;
    Consultorio consultorio;

    public Cita(String id, Usuario usuario, Paciente paciente, java.sql.Date fecha, LocalTime hora, Consultorio consultorio) {
        this.id = id;
        this.usuario = usuario;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.consultorio = consultorio;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }
}
