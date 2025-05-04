package com.consultorio.util.alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertaAprobacion {

    public void ventanaAprobacion(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Advertencia Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        // Remover botones extra (solo dejar "OK")
        alerta.getButtonTypes().setAll(ButtonType.OK);

        alerta.showAndWait(); // Mostrar y esperar que el usuario cierre
    }
}
