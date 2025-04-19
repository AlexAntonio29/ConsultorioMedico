package com.consultorio.modelo.movimiento.atencion;

import com.consultorio.modelo.clientes.Paciente;
import com.consultorio.modelo.movimiento.atencion.proceso.Consulta;
import com.consultorio.modelo.movimiento.atencion.proceso.PrescripcionElectronica;
import com.consultorio.modelo.movimiento.atencion.proceso.Registro;

import java.util.Date;

public class atencion {
    public int id;
    public Paciente paciente;
    Consulta consulta;
    public Registro registro;
    public PrescripcionElectronica prescripcion;
    Date fIngreso;

    public atencion(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Registro getRegistro() {
        return registro;

    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public PrescripcionElectronica getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(PrescripcionElectronica prescripcion) {
        this.prescripcion = prescripcion;
    }

    public Date getfIngreso() {
        return fIngreso;

    }
    public void setfIngreso(Date fIngreso) {
        this.fIngreso = fIngreso;
    }


}
