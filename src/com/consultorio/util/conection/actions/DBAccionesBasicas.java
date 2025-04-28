package com.consultorio.util.conection.actions;

import com.consultorio.util.alertas.errores.VentanaErrores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAccionesBasicas {

    VentanaErrores ventanaErrores = new VentanaErrores();

    Connection connection;

    public DBAccionesBasicas() {

    }

    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public boolean verificarTablaVacia(String nombreTabla){


      //  String sql= "SELECT * FROM "+nombreTabla;
        String sql= "SELECT COUNT(*) FROM " +nombreTabla;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            return rs.getInt(1)==0;//si count es 0, la tabla esta vacia

        }catch (SQLException e) {
            //
            // throw new RuntimeException(e);
           // ventanaErrores.ventanaErrorClasico("Error en SQL "+ e.getMessage());
            e.printStackTrace();
            return false;//si da error no esta vacia
        }

    }






}
