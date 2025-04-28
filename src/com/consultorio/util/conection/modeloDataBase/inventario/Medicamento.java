package com.consultorio.util.conection.modeloDataBase.inventario;

public class Medicamento extends Inventario {

    String regulacion;


    public Medicamento(){
        super();

    }

    public String getRegulacion() {
        return regulacion;
    }



    public void setRegulacion(String regulacion) {
        this.regulacion = regulacion;
    }


}
