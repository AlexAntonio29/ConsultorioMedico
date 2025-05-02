package com.consultorio.util.alertas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerta {




    public boolean mostrarConfirmacion(String message) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar acción");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText(message);

        final Boolean[] confirmar = {true};

        // Mostrar la alerta y manejar la respuesta del usuario
        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                System.out.println("El usuario confirmó la acción.");
               confirmar[0] =true;
            } else {
                System.out.println("El usuario canceló la acción.");
               confirmar[0] =false;
            }
        });

        return confirmar[0];
    }


    public void AccionExitosa(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Accion Exitosa");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        // Remover botones extra (solo dejar "OK")
        alerta.getButtonTypes().setAll(ButtonType.OK);

        alerta.showAndWait(); // Mostrar y esperar que el usuario cierre
    }
}
