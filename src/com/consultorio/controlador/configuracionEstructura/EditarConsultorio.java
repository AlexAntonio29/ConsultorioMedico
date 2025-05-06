package com.consultorio.controlador.configuracionEstructura;

import com.consultorio.modelo.estructura.Consultorio;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.alertas.Alerta;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;
import com.consultorio.util.conection.modeloDataBase.estructura.ConsultorioDB;
import com.consultorio.util.conection.modeloDataBase.estructura.EdificioDB;
import com.consultorio.util.conection.modeloDataBase.estructura.RegistroConsultorioDB;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditarConsultorio {


    ArrayList<Consultorio> listaConsultorio = new ArrayList<>();

    @FXML
    TextField tfBusqueda;

    @FXML
    AnchorPane rootPanel;

    Consultorio  seleccionado;

    @FXML
    TableView<Consultorio> tvConsultorio;



    @FXML
    GridPane GridPaneFormulario;
    //AGREGAR LOS TEXTFIELD


    @FXML TextField tfNumConsultorio;
    @FXML
    ComboBox cbEspecialidad;
    @FXML ComboBox cbIdEdificio;
    @FXML TextField tfNumPiso;

    @FXML
    HBox hboxBusqueda;





    @FXML
    Button btnEditar;
    @FXML Button btnEliminar;
    @FXML Button btnGuardar;
    @FXML Button btnCancelar;







    //valores de cada columna
    @FXML private TableColumn<Consultorio, String> colNCounsltorio;
    @FXML private TableColumn<Consultorio, String> colEspecialidad;
    @FXML private TableColumn<Consultorio, String> colDisponibilidad;
    @FXML private TableColumn<Consultorio, String> colIdEdificio;
    @FXML private TableColumn<Consultorio, String> colNumPiso;






    Boolean esPropietario=false;



    Alerta alerta = new Alerta();
    AlertaAprobacion alertaAprobacion= new AlertaAprobacion();
    VentanaErrores error= new VentanaErrores();


    ConsultorioDB consultorioDB = new ConsultorioDB();
    RegistroConsultorioDB registroConsultorioDB = new RegistroConsultorioDB();
    EdificioDB edificioDB = new EdificioDB();

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
    public EditarConsultorio(){}

    @FXML
    public void initialize() {



        //cuando es seleccionado el objeto Consultorio
        tvConsultorio.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getSelected(); // Llamar a una función
            }
        });

        Platform.runLater(()->{
            edificioDB.setConnection(connection);
            registroConsultorioDB.setConnection(connection);
            consultorioDB.setConnection(connection);

            cargar();

            if (Objects.equals(usuario.getId(), "1")) esPropietario=true;

            //obtener Consultorio de la base de datos
            getConsultorio();

        });
    }

    //OBJETO SELECCIONADO
    public void getSelected(){
        seleccionado = tvConsultorio.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {



            btnEditar.setDisable(false);
            btnEliminar.setDisable(false);
            //btnGuardar.setDisable(false);
            //btnCancelar.setDisable(false);




            try {


                tfNumConsultorio.setText(seleccionado.getNConsultorio());
                cbEspecialidad.setValue(seleccionado.getEspecialidad());
                cbIdEdificio.setValue(seleccionado.getEdificio().getId());
                tfNumPiso.setText(seleccionado.getNumeroPiso());







            }catch (Exception e){
                System.out.println(e.getMessage());
            }





        } else {
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
            System.out.println("No hay fila seleccionada.");
        }
    }

    public void getConsultorio(){
        tvConsultorio.getItems().clear();
        listaConsultorio.clear();
        List<Consultorio> consultorios = consultorioDB.getConsultorios();

        for (Consultorio c: consultorios) {
            Consultorio newConsultorio = c;
            if (esPropietario|| esMiConsultorio(usuario.getId(),newConsultorio.getNConsultorio())){

                listaConsultorio.add(c);
            }
        }

        imprimirConsultorio();


    }

    public Boolean esMiConsultorio(String idUsuario, String idConsultorio){

        try {
            return registroConsultorioDB.existeRegistroConsultorio((idUsuario) ,(idConsultorio));
        }catch (Exception e){
            e.printStackTrace();
            error.ventanaErrorClasico("Error al capturar datos u obtener registroConsultorio");
        }
        return false;

    }

    public void imprimirConsultorio(){

        try {
            configurarColumnas();

            System.out.println(listaConsultorio);
            System.out.println(tvConsultorio);

            tvConsultorio.setItems(FXCollections.observableArrayList(listaConsultorio));


        }catch (Exception e){
            e.printStackTrace();
            error.ventanaErrorClasico("Error al imprimir Consultorio");
        }



    }

    private void configurarColumnas() {
        colNCounsltorio.setCellValueFactory(new PropertyValueFactory<>("nConsultorio"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));
        colIdEdificio.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEdificio().getId()));

        colNumPiso.setCellValueFactory(new PropertyValueFactory<>("numeroPiso"));
    }


    //_______________________________________________________________________________

    //ACCIONES POR DEFECTO
    public void cargar(){



        tvConsultorio.setVisible(true);
        tvConsultorio.setManaged(true);
        hboxBusqueda.setVisible(true);
        GridPaneFormulario.setVisible(false);
        GridPaneFormulario.setManaged(false);

        cargarItemsComboBoxIDEdificio();



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

    @FXML public void actionEliminarConsultorio(){
        try {


            if (alerta.mostrarConfirmacion("¿Desea Eliminar al Edificio?")){
                consultorioDB.eliminarConsultorio(Integer.parseInt(seleccionado.getNConsultorio()));
                alerta.AccionExitosa("Consultorio eliminado exitosamente");

                recargarFXML();

            }
            else {
                System.out.println("operacion eliminar Consultorio cancelada");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERROR REVISAR EN actionEliminarConsultorio");
        }

    }
    @FXML public void actionEditarConsultorio(){
        tvConsultorio.setVisible(false);
        tvConsultorio.setManaged(false);
        hboxBusqueda.setVisible(false);

        GridPaneFormulario.setVisible(true);
        GridPaneFormulario.setManaged(true);
        btnEditar.setDisable(true);
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);

    }
    @FXML public void actionGuardarConsultorio(){
        try {

            Consultorio consultorioModificado = seleccionado;


            consultorioModificado.setNConsultorio(tfNumConsultorio.getText());
            consultorioModificado.setEspecialidad(cbEspecialidad.getValue().toString());
            consultorioModificado.setEdificio(edificioDB.getEdificio(Integer.parseInt((String) cbIdEdificio.getValue())));
            consultorioModificado.setNumeroPiso(tfNumPiso.getText());





            if (alerta.mostrarConfirmacion("¿Desea Modificar al Consultorio?")){
                consultorioDB.updateConsultorio(consultorioModificado);
                alerta.AccionExitosa("Consultorio Modificado exitosamente");

                recargarFXML();

            }
            else {
                System.out.println("operacion eliminar Consultorio cancelada");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERROR REVISAR EN actionEliminar Consultorio");
        }

    }
    @FXML public void actionCancelarConsultorio(){
        tvConsultorio.setVisible(true);
        tvConsultorio.setManaged(true);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/consultorio/vista/configuracionEstructura/editar_consultorio.fxml"));
            Parent nuevoContenido = loader.load();
            EditarConsultorio controller = loader.getController();
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
            List<Consultorio> resultados = consultorioDB.buscarConsultorio(textoBusqueda);
            tvConsultorio.getItems().setAll(resultados); // Actualizar la tabla con los resultados
        }else getConsultorio();
    }


    public void cargarItemsComboBoxIDEdificio(){

       // List<Edificio> lista = edificioDB.getEdificios();
        List<String> listaId = edificioDB.getIdEdificios();

        cbIdEdificio.setItems(FXCollections.observableArrayList(listaId));
    }
}
