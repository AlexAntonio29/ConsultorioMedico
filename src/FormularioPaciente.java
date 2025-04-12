
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javax.swing.*;


public class FormularioPaciente {

    private VBox vbox;

    public FormularioPaciente() {
        Label nombreEtiqta = new Label("Nombre: ");
        Label aPaternoEtiqta = new Label("Apellido Paterno: ");
        Label aMaternoEtiqta = new Label("Apellido Materno: ");
        Label fechaNacEtiqta = new Label("Fecha de Nacimiento: ");
        Label direccionEtiqta = new Label("Direccion: ");
        Label telefonoEtiqta = new Label("Telefono: ");

        TextField campoNombre = new TextField();
        TextField campoApaterno = new TextField();
        TextField campoAmaterno = new TextField();
        TextField campoFnacimiento = new TextField();
        TextField campoDireccion = new TextField();
        TextField campoTelefono = new TextField();

        Button btnGuardar = new Button("Guardar");

       btnGuardar.setOnAction(e -> {
            String nombrePaciente = campoNombre.getText();
            String amatPaciente = campoApaterno.getText();
            String apatPaciente = campoAmaterno.getText();
            String fechaPaciente = campoFnacimiento.getText();
            String dirPaciente = campoDireccion.getText();
            String telefonoPaciente = campoTelefono.getText();

           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setHeaderText(null);  // Sin encabezado
           alert.setContentText("El paciente " + nombrePaciente + " se ha guardado correctamente");
           alert.showAndWait();

            campoNombre.clear();
            campoApaterno.clear();
            campoAmaterno.clear();
            campoFnacimiento.clear();
            campoDireccion.clear();
            campoTelefono.clear();
        });

        // Crear un contenedor vertical (VBox)
        vbox = new VBox(20);
        vbox.getChildren().addAll(nombreEtiqta,aPaternoEtiqta,aMaternoEtiqta,fechaNacEtiqta,direccionEtiqta,telefonoEtiqta,campoNombre,campoApaterno,campoAmaterno,campoFnacimiento,campoDireccion,campoTelefono, btnGuardar);

    }

    public VBox getRoot() {
        return vbox;
    }


}
