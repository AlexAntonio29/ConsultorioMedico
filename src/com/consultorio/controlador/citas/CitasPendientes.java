package com.consultorio.controlador.citas;

import java.sql.Connection;

public class CitasPendientes {
    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }
}
