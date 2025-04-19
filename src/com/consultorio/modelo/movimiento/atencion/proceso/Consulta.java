package com.consultorio.modelo.movimiento;

import com.consultorio.modelo.clientes.Paciente;

import java.util.Date;

public class Consulta {

    public int id;
    public Paciente paciente;
    public String descripcion;
    public Registro registro;
    public PrescripcionElectronica prescripcion;
    Date fIngreso;


    public Consulta(){

    }

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
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
