package com.consultorio.controlador.configuracion;

import java.sql.Connection;

public class DatosConsultorio
{

    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }
}
