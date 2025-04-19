package com.consultorio.modelo.movimiento.atencion.proceso;

import com.consultorio.modelo.inventario.Medicamento;

public class PrescripcionElectronica {
    Medicamento medicamento;
    String dosisFrecuencia;

    public PrescripcionElectronica(){}

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosisFrecuencia() {
        return dosisFrecuencia;
    }

    public void setDosisFrecuencia(String dosisFrecuencia) {
        this.dosisFrecuencia = dosisFrecuencia;
    }
}
