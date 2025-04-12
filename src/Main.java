

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Crear una instancia de FormularioPaciente si necesitas usarlo
        // FormularioPaciente formulario = new FormularioPaciente();

        // Crear una escena y asignar un tamaño básico
        Scene scene = new Scene(new VBox(), 400, 400); // Ajusta el tamaño de la ventana

        // Configurar el título de la ventana
        primaryStage.setTitle("Formulario de Paciente");

        // Establecer la escena en el escenario (ventana)
        primaryStage.setScene(scene);

        // Mostrar el escenario
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        //hola
    }
}

