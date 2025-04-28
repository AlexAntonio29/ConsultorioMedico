package com.consultorio.controlador.inventario;

import java.sql.Connection;

public class RegistroInsumos {

    //Conector para base de datos
    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }
    public RegistroInsumos(){}
}
