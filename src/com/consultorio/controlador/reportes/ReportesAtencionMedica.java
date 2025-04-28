package com.consultorio.controlador.reportes;

import java.sql.Connection;

public class ReportesAtencionMedica {
    //Conector para base de datos
    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }
    public ReportesAtencionMedica() {}
}
