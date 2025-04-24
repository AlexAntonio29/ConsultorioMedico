package com.consultorio.util.conection.modeloDataBase.movimiento.movimientoMedico;

import com.consultorio.util.conection.modeloDataBase.clientes.DBPaciente;
import com.consultorio.util.conection.modeloDataBase.estructura.Consultorio;
import com.consultorio.util.conection.modeloDataBase.personal.EmpleadoDB;

import java.time.LocalTime;
import java.util.Date;

public class Cita {

    EmpleadoDB idUsuario;
    DBPaciente idPaciente;
    Date fecha;
    LocalTime hora;
    Consultorio nConsultorio;

    public EmpleadoDB getIdUsuario() {
        return idUsuario;
    }

    public DBPaciente getIdPaciente() {
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

    public void setIdUsuario(EmpleadoDB idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdPaciente(DBPaciente idPaciente) {
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
