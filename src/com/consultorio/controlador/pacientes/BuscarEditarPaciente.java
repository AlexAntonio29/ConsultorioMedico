package com.consultorio.controlador.pacientes;

import com.consultorio.modelo.clientes.Paciente;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.AlertaConfirmacion;
import com.consultorio.util.alertas.errores.VentanaErrores;
import com.consultorio.util.conection.modeloDataBase.clientes.PacienteDB;
import com.consultorio.util.conection.modeloDataBase.clientes.RegistroPacienteDB;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;


import java.awt.*;
import java.io.File;
import java.security.PublicKey;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class BuscarEditarPaciente {


    String foto="";
    File fileFoto=null;
    String dirImage="";
    @FXML
    Button btnCargarImg;
  ArrayList<Paciente> listaPaciente = new ArrayList<>();

  @FXML
    AnchorPane rootPanel;

  Paciente seleccionado;
    @FXML
    Button btnBuscar;
    @FXML
    TableView<Paciente> tvPacientes;
    @FXML
    ImageView ivFotoCamera;


    //AGREGAR LOS TEXTFIELD

    @FXML ImageView ivFoto;
    @FXML TextField tfNombre;
    @FXML TextField tfApellidoPaterno;
    @FXML TextField tfApellidoMaterno;
    @FXML DatePicker dpFechaNacimiento;
    @FXML TextField tfCurp;
    @FXML TextField tfDireccion;
    @FXML TextField tfTelefono;
    @FXML ComboBox comboTipoSexo;
    @FXML TextField tfEmail;



    @FXML Button btnEditar;
    @FXML Button btnEliminar;







            //valores de cada columna
            @FXML private TableColumn<Paciente, String> colID;
    @FXML private TableColumn<Paciente, String> colNombre;
    @FXML private TableColumn<Paciente, String> colApellidoPaterno;
    @FXML private TableColumn<Paciente, String> colApellidoMaterno;
    @FXML private TableColumn<Paciente, Date> colFechaNacimiento;
    @FXML private TableColumn<Paciente, String> colCurp;
    @FXML private TableColumn<Paciente, String> colDireccion;
    @FXML private TableColumn<Paciente, String> colTelefono;
    @FXML private TableColumn<Paciente, String> colSexo;
    @FXML private TableColumn<Paciente, String> colEdad;
    @FXML private TableColumn<Paciente, String> colEmail;
    @FXML private TableColumn<Paciente, String> colFoto;


    @FXML
    public void actionbtnCamera(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona Imagen");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        //agregar variable al archivo

        fileFoto = fileChooser.showOpenDialog(null);

        if (fileFoto != null) {
            foto = fileFoto.toURI().toString();
            Image image = new Image(foto);
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



    AlertaConfirmacion alerta = new AlertaConfirmacion();
    AlertaAprobacion alertaAprobacion= new AlertaAprobacion();
    VentanaErrores error= new VentanaErrores();


    PacienteDB pacienteDB = new PacienteDB();
    RegistroPacienteDB registroPacienteDB = new RegistroPacienteDB();


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
    public BuscarEditarPaciente(){}

    @FXML
    public void initialize() {
        cargar();

        //cuando es seleccionado el objeto paciente
        tvPacientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getSelected(); // Llamar a una función
            }
        });

        Platform.runLater(()->{



            if (Objects.equals(usuario.getId(), "1")) esPropietario=true;


            pacienteDB.setConnection(connection);
            registroPacienteDB.setConnection(connection);

            //obtener paciente de la base de datos
            getPacientes();

        });
    }

    //OBJETO SELECCIONADO
    public void getSelected(){
        seleccionado =tvPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {

            System.out.println("Seleccionaste: " + seleccionado.getNombre());

            btnEditar.setDisable(false);
            btnEliminar.setDisable(false);


            //AGREGAR DATOS A LA

            //AGREGAR IMAGEN POR DEFECTO

            try {
                if (!Objects.equals(seleccionado.getFoto(), "")) {
                    String url = "file:/"+seleccionado.getFoto();
                    Image image = new Image(url);
                    ivFoto.setImage(image);
                    System.out.println("Foto seleccionado");
                    System.out.println("Ruta: "+foto);
                    ivFoto.setPreserveRatio(true);
                    ivFoto.setFitWidth(100);
                    ivFoto.setFitHeight(100);
                    ivFoto.setPreserveRatio(false);
                    ivFoto.setSmooth(true);
                //pegar demas datos

                    tfNombre.setText(seleccionado.getNombre());
                    tfApellidoPaterno.setText(seleccionado.getAPaterno());
                    tfApellidoMaterno.setText(seleccionado.getAPaterno());
                    if (seleccionado.getFnacimiento()!=null) System.out.println("fecha Correcta");
                    else System.out.println("Fecha mal");
                    String localDate = String.valueOf(seleccionado.getFnacimiento());

                    dpFechaNacimiento.setValue(LocalDate.parse(localDate));
                    tfCurp.setText(seleccionado.getCurp());
                    tfDireccion.setText(seleccionado.getDireccion());
                    tfTelefono.setText(seleccionado.getTelefono());
                    comboTipoSexo.setValue(seleccionado.getSexo());
                    tfEmail.setText(seleccionado.getEmail());
                    String edad = seleccionado.getEdad();



                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }





        } else {
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
            System.out.println("No hay fila seleccionada.");
        }
    }

    public void getPacientes(){




        int contador=1;

        Paciente paciente;
        while ((paciente= pacienteDB.getPaciente(contador))!=null){

            Paciente newPaciente = paciente;

            if (esPropietario||esMiPaciente(usuario.getId(),newPaciente.getId())){
              //  imprimirPacientes(newPaciente);
                listaPaciente.add(paciente);
            }
            contador++;
        }

        imprimirPacientes();


    }

    public Boolean esMiPaciente(String idUsuario, String idPaciente){

        try {
            return registroPacienteDB.existeRegistroPaciente(Integer.parseInt(idUsuario) ,Integer.parseInt(idPaciente));
        }catch (Exception e){
            e.printStackTrace();
            error.ventanaErrorClasico("Error al capturar datos u obtener registroPaciente");
        }
        return false;

  }

    public void imprimirPacientes(){

        try {
            configurarColumnas();

            System.out.println(listaPaciente);
            System.out.println(tvPacientes);

            tvPacientes.setItems(FXCollections.observableArrayList(listaPaciente));


        }catch (Exception e){
            e.printStackTrace();
            error.ventanaErrorClasico("Error al imprimir Paciente");
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

    @FXML public void actionEliminarPaciente(){
try {


        if (alerta.mostrarConfirmacion("¿Desea Eliminar al paciente?")){
       // pacienteDB.eliminarPaciente(Integer.parseInt(seleccionado.getId()));
        alertaAprobacion.ventanaAprobacion("Paciente eliminado exitosamente");

        recargarFXML();

        }
        else {
            System.out.println("operacion eliminar paciente cancelada");
        }
}catch (Exception e){
    System.out.println(e.getMessage());
    System.out.println("ERROR REVISAR EN actionEliminarPaciente");
}

    }


    public void recargarFXML(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/consultorio/vista/pacientes/buscar_editar_paciente.fxml"));
            Parent nuevoContenido = loader.load();

            rootPanel.getChildren().clear();
            rootPanel.getChildren().add(nuevoContenido);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
