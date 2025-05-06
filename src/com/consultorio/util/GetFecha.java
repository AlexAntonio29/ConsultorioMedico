package com.consultorio.util;

import javafx.scene.control.DatePicker;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class GetFecha {

    public GetFecha(){

    }

    public boolean esFechaHoraValida(DatePicker datePicker, LocalTime hora) {
        LocalDate fechaSeleccionada = datePicker.getValue();

        if (fechaSeleccionada == null || hora == null) {
            System.out.println("❌ La fecha o la hora no son válidas.");
            return false;
        }

        LocalDateTime fechaHoraSeleccionada = LocalDateTime.of(fechaSeleccionada, hora);
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        return fechaHoraSeleccionada.isAfter(fechaHoraActual);
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

    public Date convertirDatePickerADate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue(); // Obtener LocalDate
        return (localDate != null) ? Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }

    public void convertirDateADatePicker(Date date, DatePicker datePicker) {
        if (date != null) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            datePicker.setValue(localDate); // Asigna la fecha al DatePicker
        }


    }


    public String calcularEdad(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            return "Fecha de nacimiento no válida";
        }

        // Convertir Date a LocalDate
        LocalDate fechaNac = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Calcular la diferencia en años
        int edad = Period.between(fechaNac, fechaActual).getYears();

        return String.valueOf(edad); // Convertir a String y devolver
    }

}
