package com.consultorio.util.alertas.errores;

import javafx.scene.control.Alert;

public class VentanaErrores {
    public VentanaErrores(){}

    public void ventanaErrorClasico(String mensaje){
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Error");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText(mensaje);
        confirmacion.showAndWait();

    }
}
