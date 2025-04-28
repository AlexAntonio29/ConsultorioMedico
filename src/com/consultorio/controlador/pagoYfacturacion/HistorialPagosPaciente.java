package com.consultorio.controlador.pagoYfacturacion;

import java.sql.Connection;

public class HistorialPagosPaciente {

    //Conector para base de datos
    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }
    public HistorialPagosPaciente(){}
}
