package com.consultorio.modelo.movimiento.movimientoMedico;

import com.consultorio.modelo.estructura.Consultorio;
import com.consultorio.modelo.personal.Empleado;
import com.consultorio.modelo.clientes.Paciente;

import java.time.LocalTime;
import java.util.Date;

public class Cita {

    Empleado idUsuario;
    Paciente idPaciente;
    Date fecha;
    LocalTime hora;
    Consultorio nConsultorio;

    public Empleado getIdUsuario() {
        return idUsuario;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Consultorio getnConsultorio() {
        return nConsultorio;
    }

    public void setIdUsuario(Empleado idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setnConsultorio(Consultorio nConsultorio) {
        this.nConsultorio = nConsultorio;
    }
}
