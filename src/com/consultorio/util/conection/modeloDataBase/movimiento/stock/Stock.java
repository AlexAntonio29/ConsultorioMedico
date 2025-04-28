package com.consultorio.util.conection.modeloDataBase.movimiento.stock;

import com.consultorio.util.conection.modeloDataBase.personal.UsuarioDB;

import java.util.Date;

public class Stock {

    public String id;
    public UsuarioDB responsable;
    public String movimiento;//si es stock de medicamento o insumo
    public Date fMovimiento;
    public String razon;//descripcion de porque se receto ese medicamento


    public String getId() {
        return id;
    }

    public UsuarioDB getResponsable() {
        return responsable;
    }

    public Date getfMovimiento() {
        return fMovimiento;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public String getRazon() {
        return razon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResponsable(UsuarioDB responsable) {
        this.responsable = responsable;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public void setfMovimiento(Date fMovimiento) {
        this.fMovimiento = fMovimiento;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
}
