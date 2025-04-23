package com.consultorio.util.conection.modeloDataBase.personal;

import com.consultorio.modelo.personal.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDB {



    Connection connection;

    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public boolean existeUsuario(String nombreTabla, String nombreUsuario){
        String sql="SELECT COUNT(*) FROM " +nombreTabla+" WHERE usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();//ejecutar consulta
            if (rs.next()) {


            }
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("no se encontro usuario "+ nombreUsuario);
            return false;
        }

    }




    public Usuario getUsuario( String nombreTabla, String nombreUsuario){
        Usuario usuario = new Usuario();
        //tomar usuario de base de datos y mandarlo a donde se ocupe

        String sql="SELECT COUNT(*) FROM " +nombreTabla+" WHERE usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();//ejecutar consulta
            if (rs.next()) {
                


            }
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("no se encontro usuario "+ nombreUsuario);
            return false;
        }
        return usuario;

    }

    public void setUsuario(){
        //agregar usuario a base de datos



    }
   }
