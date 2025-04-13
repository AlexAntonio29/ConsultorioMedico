package com.consultorio.modelo;

import java.util.Date;

public class Paciente {
    String id;
    String nombre;
    String aPterno;
    String aMaterno;
    Date fnacimiento;
    String direccion;
    String telefono;

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getaPterno() {
        return aPterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setaPterno(String aPterno) {
        this.aPterno = aPterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
