package com.consultorio.util.alertas;

import javafx.scene.control.Alert;

public class AlertaAprobacion {

    public void ventanaAprobacion(String mensaje){
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Registro Exitoso");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText(mensaje);
        confirmacion.showAndWait();

    }
}
