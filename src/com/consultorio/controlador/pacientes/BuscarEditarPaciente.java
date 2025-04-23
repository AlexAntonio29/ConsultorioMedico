package com.consultorio.controlador.pacientes;

import java.sql.Connection;

public class BuscarEditarPaciente {

    //Conector para base de datos
    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }
    public BuscarEditarPaciente(){}
}
