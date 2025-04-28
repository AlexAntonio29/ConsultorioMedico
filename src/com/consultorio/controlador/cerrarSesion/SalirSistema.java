package com.consultorio.controlador.cerrarSesion;

import java.sql.Connection;
import java.sql.SQLException;

public class SalirSistema {

    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){

        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public void closeConection(){
        try {
            connection.close();
            System.out.println("Conexion cerrada");
        }catch (SQLException e){
            e.printStackTrace();

        }
    }
}
