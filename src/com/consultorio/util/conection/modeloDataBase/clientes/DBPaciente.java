package com.consultorio.util.conection.modeloDataBase.clientes;

import com.consultorio.util.conection.modeloDataBase.modelo.Persona;

import java.util.Date;

public class DBPaciente extends Persona {

    public DBPaciente() {}
    public DBPaciente(String id, String curp, String nombre, String aPaterno
            , String aMaterno, Date fnacimiento, String direccion , String telefono , String email
            , String foto){
      super(id, curp, nombre, aPaterno, aMaterno, fnacimiento, direccion, telefono, email, foto);


    }

}
