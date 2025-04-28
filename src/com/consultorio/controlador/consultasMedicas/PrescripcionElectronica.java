package com.consultorio.controlador.consultasMedicas;

import java.sql.Connection;

public class PrescripcionElectronica {

    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }
}
