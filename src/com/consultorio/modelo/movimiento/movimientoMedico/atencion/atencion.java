package com.consultorio.modelo.movimiento.movimientoMedico.atencion;

import com.consultorio.modelo.clientes.Paciente;
import com.consultorio.modelo.movimiento.movimientoMedico.atencion.proceso.Consulta;
import com.consultorio.modelo.movimiento.movimientoMedico.atencion.proceso.PrescripcionElectronica;
import com.consultorio.modelo.movimiento.movimientoMedico.atencion.proceso.Registro;
import com.consultorio.modelo.personal.Empleado;
import com.consultorio.modelo.personal.Usuario;

import java.util.Date;

public class atencion {
    public int id;
    public Usuario medico;//quien es usuario
    public Paciente paciente;
    public Consulta consulta;
    public Registro registro;
    public PrescripcionElectronica prescripcion;
    public Date fIngreso;

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
