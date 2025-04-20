package com.consultorio.modelo.personal;

import java.util.Date;

public class Empleado{
    String id;
    String nombre;
    String aPaterno;
    String aMaterno;
    Date fnacimiento;
    String ocupacion;
    String especialidad;
    String direccion;
    String telefono;
    String email;
    String foto;

    public Empleado(){

    }


    public String getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getFoto() {
        return foto;
    }

    public void  setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public void setOcupacion(String especialidad) {
        this.ocupacion = especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

