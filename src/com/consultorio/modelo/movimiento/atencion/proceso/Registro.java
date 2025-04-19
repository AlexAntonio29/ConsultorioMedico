package com.consultorio.modelo.movimiento.atencion.proceso;

public class Registro {
    public String sintomas;
    public String Diagnostico;
    public String Tratamiento;

    public Registro(){
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getDiagnostico() {
        return Diagnostico;
    }

    public String getTratamiento() {
        return Tratamiento;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setTratamiento(String tratamiento) {
        Tratamiento = tratamiento;
    }

    public void setDiagnostico(String diagnostico) {
        Diagnostico = diagnostico;
    }
}
