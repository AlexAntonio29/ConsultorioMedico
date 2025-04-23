package com.consultorio.controlador.pacientes;

import javafx.fxml.FXML;

import java.sql.Connection;

public class HistorialClinico {

    //Conector para base de datos
    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }
    public HistorialClinico(){}

    @FXML
     public void initialize() {



    }
}
