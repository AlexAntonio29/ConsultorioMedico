package com.consultorio.modelo.modelo;

import java.util.Date;

public class Persona {

    private String id;
    private String curp;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private Date fnacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private  String foto;

    private String edad;
    private String sexo;


    public Persona() {}

    public Persona(String id,String curp, String nombre, String aPaterno
    , String aMaterno, Date fnacimiento, String direccion , String telefono , String email
    , String foto, String edad,String sexo){
        this.id=id;
        this.curp=curp;
        this.nombre=nombre;
        this.aPaterno=aPaterno;
        this.aMaterno=aMaterno;
        this.fnacimiento=fnacimiento;
        this.direccion=direccion;
        this.telefono=telefono;
        this.email=email;
        this.foto=foto;
        this.edad=edad;
        this.sexo=sexo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCurp() {
        return curp;
    }
    public void setCurp(String curp) {
        this.curp = curp;

    }
    public String getNombre() {
        return nombre;

    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getAPaterno() {
        return aPaterno;

    }
    public void setAPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }
    public String getAMaterno() {
        return aMaterno;
    }
    public void setAMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }
    public Date getFnacimiento() {
        return fnacimiento;
    }
    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;

    }
    public String getEmail() {
        return email;

    }
    public void setEmail(String email) {
        this.email = email;

    }
    public String getFoto() {
        return foto;

    }
    public void setFoto(String foto) {
        this.foto = foto;

    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


}
