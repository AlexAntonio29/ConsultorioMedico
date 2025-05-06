package com.consultorio.controlador.usuarioPersonal;


import com.consultorio.modelo.personal.Empleado;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.GetFecha;
import com.consultorio.util.alertas.Alerta;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;
import com.consultorio.util.conection.modeloDataBase.personal.EmpleadoDB;
import com.consultorio.util.conection.modeloDataBase.personal.RegistroEmpleadoDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PersonalGeneral {


    String foto="";
    File fileFoto=null;
    String dirImage="";
    @FXML
    Button btnCargarImg;
    ArrayList<Empleado> listaEmpleado = new ArrayList<>();

    @FXML
    TextField tfBusqueda;

    @FXML
    AnchorPane rootPanel;

    Empleado seleccionado;
    @FXML
    Button btnBuscar;
    @FXML
    TableView<Empleado> tvEmpleados;
    @FXML
    ImageView ivFotoCamera;



    @FXML
    GridPane GridPaneFormulario;
    //AGREGAR LOS TEXTFIELD

    @FXML ImageView ivFoto;
    @FXML TextField tfNombre;
    @FXML TextField tfApellidoPaterno;
    @FXML TextField tfApellidoMaterno;
    @FXML
    DatePicker dpFechaNacimiento;
    @FXML TextField tfCurp;
    @FXML TextField tfDireccion;
    @FXML TextField tfTelefono;
    @FXML
    ComboBox comboTipoSexo;
    @FXML TextField tfEmail;

    @FXML
    HBox hboxBusqueda;





    @FXML Button btnEditar;
    @FXML Button btnEliminar;
    @FXML Button btnGuardar;
    @FXML Button btnCancelar;

    @FXML ComboBox comboTipoOcupacion, comboTipoEspecialidad;







    //valores de cada columna
    @FXML private TableColumn<Empleado, String> colID;
    @FXML private TableColumn<Empleado, String> colNombre;
    @FXML private TableColumn<Empleado, String> colApellidoPaterno;
    @FXML private TableColumn<Empleado, String> colApellidoMaterno;
    @FXML private TableColumn<Empleado, Date> colFechaNacimiento;
    @FXML private TableColumn<Empleado, String> colCurp;
    @FXML private TableColumn<Empleado, String> colDireccion;
    @FXML private TableColumn<Empleado, String> colTelefono;
    @FXML private TableColumn<Empleado, String> colSexo;
    @FXML private TableColumn<Empleado, String> colEdad;
    @FXML private TableColumn<Empleado, String> colEmail;
    @FXML private TableColumn<Empleado, String> colFoto;
    @FXML private TableColumn<Empleado, String> colOcupacion;
    @FXML private TableColumn<Empleado, String> colEspecialidad;


    @FXML
    public void actionbtnCamera(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona Imagen");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        //agregar variable al archivo

        fileFoto = fileChooser.showOpenDialog(null);

        if (fileFoto != null) {
            foto=fileFoto.getAbsolutePath();
            String fotoRuta= fileFoto.toURI().toString();
            Image image = new Image(fotoRuta);

            ivFoto.setImage(image);
            System.out.println("Foto seleccionado");
            System.out.println("Ruta: "+foto);
            ivFoto.setPreserveRatio(true);
            ivFoto.setFitWidth(100);
            ivFoto.setFitHeight(100);
            ivFoto.setPreserveRatio(false);
            ivFoto.setSmooth(true);
        }



    }



    Boolean esPropietario=false;



    Alerta alerta = new Alerta();
    AlertaAprobacion alertaAprobacion= new AlertaAprobacion();
    VentanaErrores error= new VentanaErrores();


    EmpleadoDB empleadoDB = new EmpleadoDB();
    RegistroEmpleadoDB registroEmpleadoDB = new RegistroEmpleadoDB();


    public Usuario usuario;

    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }

    //Conector para base de datos
    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }
    public PersonalGeneral(){}

    @FXML
    public void initialize() {
        cargar();

        //cuando es seleccionado el objeto Empleado
        tvEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getSelected(); // Llamar a una función
            }
        });

        Platform.runLater(()->{



            if (Objects.equals(usuario.getId(), "1")) esPropietario=true;


            empleadoDB.setConector(connection);
            registroEmpleadoDB.setConnection(connection);

            //obtener Empleado de la base de datos
            getEmpleados();

        });
    }

    //OBJETO SELECCIONADO
    public void getSelected(){
        seleccionado = tvEmpleados.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {

            System.out.println("Seleccionaste: " + seleccionado.getNombre());
if (!Objects.equals(seleccionado.getId(), "1")){
            btnEditar.setDisable(false);
            btnEliminar.setDisable(false);}else {
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
            }
            //btnGuardar.setDisable(false);
            //btnCancelar.setDisable(false);


            //AGREGAR DATOS A LA

            //AGREGAR IMAGEN POR DEFECTO

            try {
                if (!Objects.equals(seleccionado.getFoto(), "")) {
                    foto = seleccionado.getFoto();
                    Image image = new Image("file:/"+foto);
                    ivFoto.setImage(image);
                    System.out.println("Foto seleccionado");
                    System.out.println("Ruta: "+foto);
                    ivFoto.setPreserveRatio(true);
                    ivFoto.setFitWidth(100);
                    ivFoto.setFitHeight(100);
                    ivFoto.setPreserveRatio(false);
                    ivFoto.setSmooth(true);
                    //pegar demas datos\
                }else {
                    ivFoto.setImage(new Image("/resource/img/user_unknown.jpg"));
                    ivFoto.setPreserveRatio(true);
                    ivFoto.setFitWidth(100);
                    ivFoto.setFitHeight(100);
                    ivFoto.setPreserveRatio(false);
                    ivFoto.setSmooth(true);
                } //dirImage="/resource/img/user_unknown.jpg"

                tfNombre.setText(seleccionado.getNombre());
                tfApellidoPaterno.setText(seleccionado.getAPaterno());
                tfApellidoMaterno.setText(seleccionado.getAMaterno());
                if (seleccionado.getFnacimiento()!=null) System.out.println("fecha Correcta");
                else System.out.println("Fecha mal");
                String localDate = String.valueOf(seleccionado.getFnacimiento());

                dpFechaNacimiento.setValue(LocalDate.parse(localDate));
                tfCurp.setText(seleccionado.getCurp());
                tfDireccion.setText(seleccionado.getDireccion());
                tfTelefono.setText(seleccionado.getTelefono());
                comboTipoSexo.setValue(seleccionado.getSexo());
                tfEmail.setText(seleccionado.getEmail());
                comboTipoOcupacion.setValue(seleccionado.getOcupacion());
                comboTipoEspecialidad.setValue(seleccionado.getEspecialidad());
                String edad = seleccionado.getEdad();




            }catch (Exception e){
                System.out.println(e.getMessage());
            }





        } else {
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
            System.out.println("No hay fila seleccionada.");
        }
    }

    public void getEmpleados(){
        tvEmpleados.getItems().clear();
        listaEmpleado.clear();
        List<Empleado> empleados = empleadoDB.getEmpleados();

        for (Empleado e: empleados) {
            Empleado newEmpleado = e;
            if (esPropietario|| esMiEmpleado(usuario.getId(),newEmpleado.getId())){

                listaEmpleado.add(e);
            }
        }

        imprimirEmpleados();


    }

    public Boolean esMiEmpleado(String idUsuario, String idEmpleado){

        try {
            return registroEmpleadoDB.existeRegistroEmpleado(Integer.parseInt(idUsuario) ,Integer.parseInt(idEmpleado));
        }catch (Exception e){
            e.printStackTrace();
            error.ventanaErrorClasico("Error al capturar datos u obtener registroEmpleado");
        }
        return false;

    }

    public void imprimirEmpleados(){

        try {
            configurarColumnas();

            System.out.println(listaEmpleado);
            System.out.println(tvEmpleados);

            tvEmpleados.setItems(FXCollections.observableArrayList(listaEmpleado));


        }catch (Exception e){
            e.printStackTrace();
            error.ventanaErrorClasico("Error al imprimir Empleado");
        }



    }

    private void configurarColumnas() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("aPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("aMaterno"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fnacimiento"));
        colCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colOcupacion.setCellValueFactory(new PropertyValueFactory<>("ocupacion"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        colFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));

        colFoto.setCellFactory(column -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String ruta, boolean empty) {
                super.updateItem(ruta, empty);

                if (empty || ruta == null || ruta.isEmpty()) {
                    setGraphic(null); // si no hay imagen, no pone nada
                } else {
                    File file = new File(ruta);
                    if (file.exists()) {
                        imageView.setImage(new Image(file.toURI().toString()));// tamaño imagen
                        //imageView.setImage(new Image(file.toURI().toString(), 100, 100, true, true));// tamaño imagen

                        imageView.setFitWidth(150);
                        imageView.setFitHeight(150);
                        imageView.setPreserveRatio(true);
                        imageView.setSmooth(true);
                        imageView.setCache(true);


                        setGraphic(imageView); // aquí se pone la imagen en la celda
                    } else {
                        setGraphic(null); // o puedes poner una imagen por defecto aquí si quieres
                    }
                }
            }
        });
    }


    //_______________________________________________________________________________

    //ACCIONES POR DEFECTO
    public void cargar(){
        cargarImgDefault();

        tvEmpleados.setVisible(true);
        tvEmpleados.setManaged(true);
        hboxBusqueda.setVisible(true);
        GridPaneFormulario.setVisible(false);
        GridPaneFormulario.setManaged(false);

        // cargarItemsEdad();
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

    public String getDataBase(String typeData){

        //llamar a la Base de datos para proporcionar direccion
        String getData="";

        return  getData;
    }



    @FXML
    public void btnBuscarOnAction() {

///

    }


    //Boton eliminar

    @FXML public void actionEliminarEmpleado(){
        try {


            if (alerta.mostrarConfirmacion("¿Desea Eliminar al Empleado?")){
                empleadoDB.eliminarEmpleado(Integer.parseInt(seleccionado.getId()));
                alerta.AccionExitosa("Empleado eliminado exitosamente");

                recargarFXML();

            }
            else {
                System.out.println("operacion eliminar Empleado cancelada");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERROR REVISAR EN actionEliminar Empleado" +
                    "");
        }

    }
    @FXML public void actionEditarEmpleado(){
        tvEmpleados.setVisible(false);
        tvEmpleados.setManaged(false);
        hboxBusqueda.setVisible(false);

        GridPaneFormulario.setVisible(true);
        GridPaneFormulario.setManaged(true);
        btnEditar.setDisable(true);
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);

    }
    @FXML public void actionGuardarEmpleado(){
        try {

            Empleado empleadoModificado = seleccionado;
            empleadoModificado.setNombre(tfNombre.getText());
            empleadoModificado.setAPaterno(tfApellidoPaterno.getText());
            empleadoModificado.setAPaterno(tfApellidoMaterno.getText());
            empleadoModificado.setFnacimiento(new GetFecha().convertirDatePickerADate(dpFechaNacimiento));
            empleadoModificado.setDireccion(tfDireccion.getText());
            empleadoModificado.setCurp(tfCurp.getText());
            empleadoModificado.setTelefono(tfTelefono.getText());
            empleadoModificado.setSexo(String.valueOf(comboTipoSexo.getValue()));
            empleadoModificado.setEdad(new GetFecha().calcularEdad(empleadoModificado.getFnacimiento()));
            empleadoModificado.setEmail(tfEmail.getText());
            empleadoModificado.setOcupacion(String.valueOf(comboTipoOcupacion.getValue()));
            empleadoModificado.setEspecialidad(String.valueOf(comboTipoEspecialidad.getValue()));
            empleadoModificado.setFoto(foto);



            if (alerta.mostrarConfirmacion("¿Desea Modificar al empleado?")){
                empleadoDB.updateEmpleado(empleadoModificado);
                alerta.AccionExitosa("Empleado Modificado exitosamente");

                recargarFXML();

            }
            else {
                System.out.println("operacion eliminar empleado cancelada");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERROR REVISAR EN actionEliminarEmpleado");
        }

    }
    @FXML public void actionCancelarEmpleado(){
        tvEmpleados.setVisible(true);
        tvEmpleados.setManaged(true);
        hboxBusqueda.setVisible(true);


        GridPaneFormulario.setVisible(false);
        GridPaneFormulario.setManaged(false);


        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(true);
    }

    public void recargarFXML(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/consultorio/vista/usuarioPersonal/personal_general.fxml"));
            Parent nuevoContenido = loader.load();
            PersonalGeneral controller = loader.getController();
            controller.setConector(connection);
            controller.setUsuario(usuario);
            rootPanel.getChildren().clear();
            rootPanel.getChildren().add(nuevoContenido);
            for (Node nodo : rootPanel.getChildren()) {
                AnchorPane.setTopAnchor(nodo, 0.0);
                AnchorPane.setBottomAnchor(nodo, 0.0);
                AnchorPane.setLeftAnchor(nodo, 0.0);
                AnchorPane.setRightAnchor(nodo, 0.0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//SECCION DE BUSQUEDA

    @FXML
    public void onBusqueda() {
        String textoBusqueda = tfBusqueda.getText().trim();

        if (!textoBusqueda.isEmpty()) {
            Task<List<Empleado>> tareaBusqueda = new Task<>() {
                @Override
                protected List<Empleado> call() {
                    return empleadoDB.buscarEmpleado(textoBusqueda); // 📌 Llamar método en un hilo aparte
                }
            };

            tareaBusqueda.setOnSucceeded(event -> {
                tvEmpleados.getItems().setAll(tareaBusqueda.getValue()); // 📌 Cargar resultados en la tabla
            });

            new Thread(tareaBusqueda).start(); // 📌 Ejecutar búsqueda en otro hilo
        } else {
            getEmpleados();
        }
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
}
