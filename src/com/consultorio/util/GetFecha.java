package com.consultorio.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class GetFecha {

    public GetFecha(){

    }

    public Date getFechaDateActual(){
        LocalDate fechaActual = LocalDate.now(); // Obtener la fecha actual
        java.util.Date date = java.sql.Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Convertir a Date
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActualString=formato.format(date);
        //empleado.setFecha_ingreso((date));
        try{
             return (formato.parse(fechaActualString));


        }catch (Exception e){
            System.out.println("Falla al asignar fechaActual");
        }
        return null;

    }
    public String getFechaStringActual(){
        LocalDate fechaActual = LocalDate.now(); // Obtener la fecha actual
        java.util.Date date = java.sql.Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Convertir a Date
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
         return formato.format(date);

    }
}
