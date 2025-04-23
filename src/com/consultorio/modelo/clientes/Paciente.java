package com.consultorio.modelo.clientes;

import com.consultorio.modelo.modelo.Persona;

import java.util.Date;

public class Paciente extends Persona {

    public Paciente() {}
    public Paciente(String id,String curp, String nombre, String aPaterno
            , String aMaterno, Date fnacimiento, String direccion , String telefono , String email
            , String foto){
      super(id, curp, nombre, aPaterno, aMaterno, fnacimiento, direccion, telefono, email, foto);


    }

}
