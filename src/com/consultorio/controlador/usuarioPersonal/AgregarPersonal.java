package com.consultorio.controlador.usuarioPersonal;

import com.consultorio.modelo.personal.Empleado;
import com.consultorio.modelo.personal.RegistroEmpleado;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.GetFecha;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;
import com.consultorio.util.conection.modeloDataBase.personal.EmpleadoDB;
import com.consultorio.util.conection.modeloDataBase.personal.RegistroEmpleadoDB;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class AgregarPersonal {

    VentanaErrores ventanaErrores = new VentanaErrores();
    AlertaAprobacion alertaAprobacion = new AlertaAprobacion();
    String mensajeError = "";

    String foto = "";
    File fileFoto = null;
    String dirImage = "";

    EmpleadoDB empleadoDB = new EmpleadoDB();
    RegistroEmpleadoDB registroEmpleadoDB= new RegistroEmpleadoDB();


    @FXML
    TextField tfNombre, tfCurp, tfApellidoPaterno, tfApellidoMaterno, tfDireccion, tfTelefono, tfEmail;
    @FXML
    DatePicker dpFechaNacimiento, dpFechaIngreso;
    @FXML
    ComboBox<String> comboTipoSexo, comboTipoOcupacion, comboTipoEspecialidad;

    @FXML
    ImageView ivFoto, ivFotoCamera;
    @FXML
    Button btnCargarImg, btnRegistrar;

    @FXML
    GridPane gridPane;



    private Connection connection;
    private Usuario usuario;

    // Configurar la conexión a la base de datos
    public void setConector(Connection connection) {
        this.connection = connection;
        empleadoDB.setConector(connection);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    public void initialize() {
        cargar();

        Platform.runLater(() -> {
            empleadoDB.setConector(connection);
            registroEmpleadoDB.setConnection(connection);
        });
    }

    // Método para registrar empleado
    @FXML
    public void actionRegistrar() {
        try {
            Empleado empleado = new Empleado();
            empleadoDB.setEmpleado(cargarEmpleado(empleado));
            registroEmpleadoDB.setRegistroEmpleado(cargarRegistroPaciente(new RegistroEmpleado(), empleado));

            alertaAprobacion.ventanaAprobacion("Registro del Empleado Exitoso");

            limpiarCampos();
        } catch (NumberFormatException e) {
            ventanaErrores.ventanaErrorClasico("Error de Formato, favor de corregirlo");
        } catch (DateTimeException e) {
            ventanaErrores.ventanaErrorClasico("Error al capturar fecha");
        } catch (NullPointerException e) {
            ventanaErrores.ventanaErrorClasico("Datos no capturados");
        } catch (Exception e) {
            ventanaErrores.ventanaErrorClasico("Error desconocido");
        }
    }

    // Método para llenar los datos del empleado
    private Empleado cargarEmpleado(Empleado empleado) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nombre = tfNombre.getText();
        String curp = tfCurp.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String fechaNacimiento = dpFechaNacimiento.getValue().toString();
        LocalDate.parse(fechaNacimiento, formato);
        String direccion = tfDireccion.getText();
        String telefono = tfTelefono.getText();
        String sexo = comboTipoSexo.getValue();
        String ocupacion = comboTipoOcupacion.getValue();
        String especialidad = comboTipoEspecialidad.getValue();
        String fechaIngreso = dpFechaIngreso.getValue().toString();
        LocalDate.parse(fechaIngreso, formato);
        String email = tfEmail.getText();
        String edad = String.valueOf(ChronoUnit.YEARS.between(dpFechaNacimiento.getValue(), LocalDate.now()));

        if (fileFoto != null) {
            String rutaDestinoFoto = "src/resource/img/empleados/";
            File carpetaDestino = new File(rutaDestinoFoto);
            if (!carpetaDestino.exists()) carpetaDestino.mkdir();

            String getIdEmpleado = curp; // Identificador basado en CURP
            File destino = new File(carpetaDestino, "empleado_" + getIdEmpleado);

            try {
                Files.copy(fileFoto.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                foto = destino.getAbsolutePath();
                System.out.println("✅ Foto guardada correctamente.");
            } catch (Exception e) {
                ventanaErrores.ventanaErrorClasico("Error al guardar foto.");
            }
        }

        empleado.setNombre(nombre);
        empleado.setCurp(curp);
        empleado.setAPaterno(apellidoPaterno);
        empleado.setAMaterno(apellidoMaterno);
        empleado.setFnacimiento(Date.valueOf(dpFechaNacimiento.getValue()));
        empleado.setDireccion(direccion);
        empleado.setTelefono(telefono);
        empleado.setSexo(sexo);
        empleado.setEdad(edad);
        empleado.setEmail(email);
        empleado.setFoto(foto);
        empleado.setOcupacion(ocupacion);
        empleado.setEspecialidad(especialidad);
        empleado.setFecha_ingreso(Date.valueOf(dpFechaIngreso.getValue()));

        return empleado;
    }

    public RegistroEmpleado cargarRegistroPaciente(RegistroEmpleado registroEmpleado, Empleado empleado){

        registroEmpleado.setIdUsuario(usuario.getId());
        registroEmpleado.setIdEmpleado(empleadoDB.obtenerUltimoIdPaciente());
        registroEmpleado.setFechaRegistro(new GetFecha().getFechaDateActual());

        return registroEmpleado;
    }


    // Método para limpiar los campos después del registro
    private void limpiarCampos() {
        tfNombre.clear();
        tfCurp.clear();
        tfApellidoPaterno.clear();
        tfApellidoMaterno.clear();
        dpFechaNacimiento.setValue(null);
        tfDireccion.clear();
        tfTelefono.clear();
        comboTipoSexo.getSelectionModel().clearSelection();
        comboTipoSexo.setPromptText("Seleccione Sexo");
        tfEmail.clear();
        comboTipoOcupacion.getSelectionModel().clearSelection();
        comboTipoOcupacion.setPromptText("Seleccione Ocupacion");
        comboTipoEspecialidad.getSelectionModel().clearSelection();
        comboTipoEspecialidad.setPromptText("Seleccione Especialidad");
        dpFechaIngreso.setValue(null);

        dirImage = "/resource/img/user_unknown.jpg";
        ivFoto.setImage(new Image(getClass().getResource(dirImage).toExternalForm()));
    }

    // Método para cargar imagen del empleado
    @FXML
    public void actionbtnCamera() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona Imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));

        fileFoto = fileChooser.showOpenDialog(null);

        if (fileFoto != null) {
            foto = fileFoto.toURI().toString();
            Image image = new Image(foto);
            ivFoto.setImage(image);
        }
    }

    public void cargar() {
        cargarImgDefault();
        //cargarComboEspecialidad();
    }

    public void cargarImgDefault(){
        //llamar metodo DataBase

        dirImage=getDataBase(dirImage);



        if (Objects.equals(dirImage, "")) dirImage="/resource/img/user_unknown.jpg";


        ivFoto.setImage(new Image(getClass().getResource(dirImage).toExternalForm()));

        ivFoto.setFitWidth(100); // Ancho máximo
        ivFoto.setFitHeight(100); // Altura máxima
        ivFoto.setPreserveRatio(true); // Mantener la proporción
        ivFoto.setSmooth(true); // Suavizado para bordes más limpios
        ivFoto.setCache(true);


        //cambiar figura del boton
        btnCargarImg.setShape(new Circle(20));
        btnCargarImg.setMinSize(20,20);
        btnCargarImg.setMaxSize(30,30);
        //cargar imagen foto
        String dirImageFoto="/resource/img/camera.png";

        ivFotoCamera.setImage(new Image(getClass().getResource(dirImageFoto).toExternalForm()));
        ivFotoCamera.setFitWidth(20);
        ivFotoCamera.setFitHeight(20);
        ivFotoCamera.setPreserveRatio(true);
        ivFotoCamera.setSmooth(true);
        ivFotoCamera.setCache(true);


        //gridPane.setAlignment(btnCargarImg, Pos.BOTTOM_RIGHT);






    }

    @FXML public void cargarComboEspecialidad(){

        comboTipoEspecialidad.getItems().clear();
        if (comboTipoOcupacion.getValue().equals("Administrador")) comboTipoEspecialidad.getItems().addAll(
                "General","Gestión Clinica", "Recursos Humanos", "Finanzas en Salud",
                "Sistemas de Información Médica", "Planificación Sanitaria", "Gestión Hospitalaria"
        );
        else
        if (comboTipoOcupacion.getValue().equals("Medico")) comboTipoEspecialidad.getItems().addAll(
                "Medicina General", "Pediatría", "Cardiología", "Neurología",
                "Dermatología", "Gastroenterología", "Ortopedia", "Oncología",
                "Ginecología y Obstetricia", "Psiquiatría"
        );
        else
        if (comboTipoOcupacion.getValue().equals("Asistente")) comboTipoEspecialidad.getItems().addAll(
                "Asistente Médico General", "Asistente en Radiología", "Asistente en Laboratorio Clínico",
                "Secretariado Médico", "Asistente en Farmacia", "Atención al Paciente"
        );
        else comboTipoEspecialidad.getItems().addAll("General"
        );



    }

    public String getDataBase(String typeData){

        //llamar a la Base de datos para proporcionar direccion
        String getData="";

        return  getData;
    }

}
