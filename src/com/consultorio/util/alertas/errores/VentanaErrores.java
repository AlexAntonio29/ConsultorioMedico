package com.consultorio.util.alertas.errores;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class VentanaErrores {
    public VentanaErrores(){}

    public void ventanaErrorClasico(String mensaje){
        {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText(mensaje);

            // Remover botones extra (solo dejar "OK")
            alerta.getButtonTypes().setAll(ButtonType.OK);

            alerta.showAndWait(); // Mostrar y esperar que el usuario cierre
        }

    }
}
