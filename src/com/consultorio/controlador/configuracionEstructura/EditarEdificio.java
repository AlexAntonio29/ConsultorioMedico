package com.consultorio.controlador.configuracionEstructura;


import com.consultorio.modelo.estructura.Edificio;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.GetFecha;
import com.consultorio.util.alertas.Alerta;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;
import com.consultorio.util.conection.modeloDataBase.estructura.EdificioDB;
import com.consultorio.util.conection.modeloDataBase.estructura.RegistroEdificioDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditarEdificio {


    ArrayList<Edificio> listaEdificios = new ArrayList<>();

    @FXML
    TextField tfBusqueda;

    @FXML
    AnchorPane rootPanel;

    Edificio seleccionado;

    @FXML
    TableView<Edificio> tvEdificios;



    @FXML
    GridPane GridPaneFormulario;
    //AGREGAR LOS TEXTFIELD


    @FXML TextField tfNumEdificio;
    @FXML TextField tfNomEdificio;
    @FXML TextField tfDireccion;
    @FXML TextField tfNumPisos;

    @FXML
    HBox hboxBusqueda;





    @FXML Button btnEditar;
    @FXML Button btnEliminar;
    @FXML Button btnGuardar;
    @FXML Button btnCancelar;







    //valores de cada columna
    @FXML private TableColumn<Edificio, String> colID;
    @FXML private TableColumn<Edificio, String> colNumEdificio;
    @FXML private TableColumn<Edificio, String> colNomEdificio;
    @FXML private TableColumn<Edificio, String> colDireccion;
    @FXML private TableColumn<Edificio, String> colNumPisos;






    Boolean esPropietario=false;



    Alerta alerta = new Alerta();
    AlertaAprobacion alertaAprobacion= new AlertaAprobacion();
    VentanaErrores error= new VentanaErrores();


    EdificioDB edificioDB = new EdificioDB();
    RegistroEdificioDB registroEdificioDB = new RegistroEdificioDB();


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
    public EditarEdificio(){}

    @FXML
    public void initialize() {
        cargar();

        //cuando es seleccionado el objeto edificio
        tvEdificios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getSelected(); // Llamar a una función
            }
        });

        Platform.runLater(()->{



            if (Objects.equals(usuario.getId(), "1")) esPropietario=true;


            edificioDB.setConnection(connection);
            registroEdificioDB.setConnection(connection);

            //obtener Edificio de la base de datos
            getEdificio();

        });
    }

    //OBJETO SELECCIONADO
    public void getSelected(){
        seleccionado = tvEdificios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {



            btnEditar.setDisable(false);
            btnEliminar.setDisable(false);
            //btnGuardar.setDisable(false);
            //btnCancelar.setDisable(false);


            //AGREGAR DATOS A LA

            //AGREGAR IMAGEN POR DEFECTO

            try {


                tfNumEdificio.setText(seleccionado.getNumeroEdificio());
                tfNomEdificio.setText(seleccionado.getNombreEdificio());
                tfDireccion.setText(seleccionado.getDireccionEdificio());
                tfNumPisos.setText(seleccionado.getNumeroPisos());







            }catch (Exception e){
                System.out.println(e.getMessage());
            }





        } else {
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
            System.out.println("No hay fila seleccionada.");
        }
    }

    public void getEdificio(){
        tvEdificios.getItems().clear();
        listaEdificios.clear();
        List<Edificio> edificios = edificioDB.getEdificio();

        for (Edificio e: edificios) {
            Edificio newEdifcio = e;
            if (esPropietario|| esMiEdificio(usuario.getId(),newEdifcio.getId())){

                listaEdificios.add(e);
            }
        }

        imprimirEdificios();


    }

    public Boolean esMiEdificio(String idUsuario, String idEdificio){

        try {
            return registroEdificioDB.existeRegistroEdificio(Integer.parseInt(idUsuario) ,Integer.parseInt(idEdificio));
        }catch (Exception e){
            e.printStackTrace();
            error.ventanaErrorClasico("Error al capturar datos u obtener registroEdificio");
        }
        return false;

    }

    public void imprimirEdificios(){

        try {
            configurarColumnas();

            System.out.println(listaEdificios);
            System.out.println(tvEdificios);

            tvEdificios.setItems(FXCollections.observableArrayList(listaEdificios));


        }catch (Exception e){
            e.printStackTrace();
            error.ventanaErrorClasico("Error al imprimir Edificio");
        }



    }

    private void configurarColumnas() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumEdificio.setCellValueFactory(new PropertyValueFactory<>("numeroEdificio"));
        colNomEdificio.setCellValueFactory(new PropertyValueFactory<>("nombreEdificio"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionEdificio"));
        colNumPisos.setCellValueFactory(new PropertyValueFactory<>("numeroPisos"));
    }


    //_______________________________________________________________________________

    //ACCIONES POR DEFECTO
    public void cargar(){


        tvEdificios.setVisible(true);
        tvEdificios.setManaged(true);
        hboxBusqueda.setVisible(true);
        GridPaneFormulario.setVisible(false);
        GridPaneFormulario.setManaged(false);

        // cargarItemsEdad();
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

    @FXML public void actionEliminarEdificio(){
        try {


            if (alerta.mostrarConfirmacion("¿Desea Eliminar al Edificio?")){
                edificioDB.eliminarEdificio(Integer.parseInt(seleccionado.getId()));
                alerta.AccionExitosa("Edificio eliminado exitosamente");

                recargarFXML();

            }
            else {
                System.out.println("operacion eliminar Edificio cancelada");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERROR REVISAR EN actionEliminarEdificio");
        }

    }
    @FXML public void actionEditarEdificio(){
        tvEdificios.setVisible(false);
        tvEdificios.setManaged(false);
        hboxBusqueda.setVisible(false);

        GridPaneFormulario.setVisible(true);
        GridPaneFormulario.setManaged(true);
        btnEditar.setDisable(true);
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);

    }
    @FXML public void actionGuardarEdificio(){
        try {

            Edificio edificioModificado = seleccionado;


            edificioModificado.setNumeroEdificio(tfNumEdificio.getText());
            edificioModificado.setNombreEdificio(tfNomEdificio.getText());
            edificioModificado.setDireccionEdificio(tfDireccion.getText());
            edificioModificado.setNumeroPisos(tfNumPisos.getText());





            if (alerta.mostrarConfirmacion("¿Desea Modificar al Edificio?")){
                edificioDB.updateEdificio(edificioModificado);
                alerta.AccionExitosa("Edificio Modificado exitosamente");

                recargarFXML();

            }
            else {
                System.out.println("operacion eliminar Edificio cancelada");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERROR REVISAR EN actionEliminarEdificio");
        }

    }
    @FXML public void actionCancelarEdificio(){
        tvEdificios.setVisible(true);
        tvEdificios.setManaged(true);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/consultorio/vista/configuracionEstructura/editar_edificio.fxml"));
            Parent nuevoContenido = loader.load();
            EditarEdificio controller = loader.getController();
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

    @FXML public void onBusqueda(){

        String textoBusqueda = tfBusqueda.getText().trim();

        if (!textoBusqueda.isEmpty()) {
            List<Edificio> resultados = edificioDB.buscarEdificio(textoBusqueda);
            tvEdificios.getItems().setAll(resultados); // Actualizar la tabla con los resultados
        }else getEdificio();
    }

}
