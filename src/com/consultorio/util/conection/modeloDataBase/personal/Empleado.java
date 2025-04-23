package com.consultorio.util.conection.modeloDataBase.personal;

import com.consultorio.util.conection.modeloDataBase.modelo.Persona;

import java.util.Date;

public class Empleado extends Persona {

    String ocupacion;
    String especialidad;
    Date fecha_ingreso;


    public Empleado(){

    }

    public Empleado(String id,String curp, String nombre, String aPaterno
            , String aMaterno, Date fnacimiento, String direccion , String telefono , String email
            , String foto,String ocupacion, String especialidad){
        super(id, curp, nombre, aPaterno, aMaterno, fnacimiento, direccion, telefono, email, foto);
        this.ocupacion=ocupacion;
        this.especialidad=especialidad;
    }



    public String getOcupacion() {
        return ocupacion;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setOcupacion(String especialidad) {
        this.ocupacion = especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void  setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

}

