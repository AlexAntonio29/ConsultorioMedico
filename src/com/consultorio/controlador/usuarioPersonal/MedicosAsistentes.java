package com.consultorio.controlador.usuarioPersonal;

import java.sql.Connection;

public class MedicosAsistentes {

    //Conector para base de datos
    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public MedicosAsistentes(){}
}
