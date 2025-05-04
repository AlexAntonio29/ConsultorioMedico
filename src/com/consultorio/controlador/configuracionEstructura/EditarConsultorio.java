package com.consultorio.controlador.configuracionEstructura;

import com.consultorio.modelo.personal.Usuario;

import java.sql.Connection;

public class EditarConsultorio {

    Connection connection;
    public Usuario usuario;

    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }


    //agregar usuario
    public void setUsuario(Usuario usuario){ this.usuario=usuario; }

}
