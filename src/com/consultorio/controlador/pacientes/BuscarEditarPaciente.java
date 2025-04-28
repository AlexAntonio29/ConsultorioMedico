package com.consultorio.controlador.pacientes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.sql.Connection;

public class BuscarEditarPaciente {

    @FXML
    Button btnBuscar;

    //Conector para base de datos
    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }
    public BuscarEditarPaciente(){}



    @FXML
    public void btnBuscarOnAction() {

///

    }
}
