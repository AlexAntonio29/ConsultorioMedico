package com.consultorio.controlador.usuarioPersonal;

import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.alertas.Alerta;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;

import com.consultorio.util.conection.modeloDataBase.personal.RegistroUsuarioDB;
import com.consultorio.util.conection.modeloDataBase.personal.UsuarioDB;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedicosAsistentes {


    ArrayList<Usuario> listaUsuario = new ArrayList<>();

    @FXML
    TextField tfBusqueda;

    @FXML
    AnchorPane rootPanel;

    Usuario seleccionado;

    @FXML
    TableView<Usuario> tvUsuario;



    @FXML
    GridPane GridPaneFormulario;
    //AGREGAR LOS TEXTFIELD


    @FXML TextField tfUsuario;
    @FXML TextField tfIDEmpleado;
    @FXML TextField tfCurp;


    @FXML
    HBox hboxBusqueda;





    @FXML
    Button btnEditar;
    @FXML Button btnEliminar;
    @FXML Button btnGuardar;
    @FXML Button btnCancelar;







    //valores de cada columna
    @FXML private TableColumn<Usuario, String> colID;
    @FXML private TableColumn<Usuario, String> colUsuario;
    @FXML private TableColumn<Usuario, String> colCurp;
    @FXML private TableColumn<Usuario, String> colIdEmpleado;
    @FXML private TableColumn<Usuario, String> colNumPisos;






    Boolean esPropietario=false;



    Alerta alerta = new Alerta();
    AlertaAprobacion alertaAprobacion= new AlertaAprobacion();
    VentanaErrores error= new VentanaErrores();


    UsuarioDB usuarioDB = new UsuarioDB();
    RegistroUsuarioDB registroUsuarioDB = new RegistroUsuarioDB();


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
    public MedicosAsistentes(){}

    @FXML
    public void initialize() {
        cargar();

        //cuando es seleccionado el objeto Usuario
        tvUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getSelected(); // Llamar a una función
            }
        });

        Platform.runLater(()->{



            if (Objects.equals(usuario.getId(), "1")) esPropietario=true;


            usuarioDB.setConector(connection);
            registroUsuarioDB.setConnection(connection);

            //obtener Usuario de la base de datos
            getUsuario();

        });
    }

    //OBJETO SELECCIONADO
    public void getSelected(){
        seleccionado = tvUsuario.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {



            btnEditar.setDisable(true);
            if(!(Objects.equals(seleccionado.getId(), "1")))btnEliminar.setDisable(false);
            else btnEliminar.setDisable(true);
            //btnGuardar.setDisable(false);
            //btnCancelar.setDisable(false);


            //AGREGAR DATOS A LA

            //AGREGAR IMAGEN POR DEFECTO

            try {









            }catch (Exception e){
                System.out.println(e.getMessage());
            }





        } else {
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
            System.out.println("No hay fila seleccionada.");
        }
    }

    public void getUsuario(){
        tvUsuario.getItems().clear();
        listaUsuario.clear();
        List<Usuario> usuario = usuarioDB.getUsuarios();

        for (Usuario u: usuario) {
            Usuario newUsuario = u;
            if (esPropietario|| esMiUsuario(this.usuario.getId(),newUsuario.getId())){

                listaUsuario.add(u);
            }
        }

        imprimirUsuarios();


    }

    public Boolean esMiUsuario(String idUsuario, String idUsuarioEditable){

        try {
            return registroUsuarioDB.existeRegistroUsuario(Integer.parseInt(idUsuario) ,Integer.parseInt(idUsuarioEditable));
        }catch (Exception e){
            e.printStackTrace();
            error.ventanaErrorClasico("Error al capturar datos u obtener registro Usuario");
        }
        return false;

    }

    public void imprimirUsuarios(){

        try {
            configurarColumnas();

            System.out.println(listaUsuario);
            System.out.println(tvUsuario);

            tvUsuario.setItems(FXCollections.observableArrayList(listaUsuario));


        }catch (Exception e){
            e.printStackTrace();
            error.ventanaErrorClasico("Error al imprimir Usuario");
        }



    }

    private void configurarColumnas() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colCurp.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEmpleado().getCurp()));

        colIdEmpleado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEmpleado().getId()));

    }


    //_______________________________________________________________________________

    //ACCIONES POR DEFECTO
    public void cargar(){


        tvUsuario.setVisible(true);
        tvUsuario.setManaged(true);
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

    @FXML public void actionEliminarUsuario(){
        try {


            if (alerta.mostrarConfirmacion("¿Desea Eliminar al Usuario?")){
                usuarioDB.eliminarUsuario(Integer.parseInt(seleccionado.getId()));
                alerta.AccionExitosa("Usuario eliminado exitosamente");

                recargarFXML();

            }
            else {
                System.out.println("operacion eliminar Usuario cancelada");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERROR REVISAR EN actionEliminarUsuario");
        }

    }
    @FXML public void actionEditarUsuario(){
        tvUsuario.setVisible(false);
        tvUsuario.setManaged(false);
        hboxBusqueda.setVisible(false);

        GridPaneFormulario.setVisible(true);
        GridPaneFormulario.setManaged(true);
        btnEditar.setDisable(true);
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);

    }
    @FXML public void actionGuardarUsuario(){
        try {

            Usuario usuarioModificado = seleccionado;








            if (alerta.mostrarConfirmacion("¿Desea Modificar al Usuario?")){
                usuarioDB.updateUsuario(usuarioModificado);
                alerta.AccionExitosa("Usuario Modificado exitosamente");

                recargarFXML();

            }
            else {
                System.out.println("operacion eliminar Usuario cancelada");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERROR REVISAR EN actionEliminarUsuario");
        }

    }
    @FXML public void actionCancelarConsultorio(){
        tvUsuario.setVisible(true);
        tvUsuario.setManaged(true);
        hboxBusqueda.setVisible(true);


        GridPaneFormulario.setVisible(false);
        GridPaneFormulario.setManaged(false);


        btnEditar.setDisable(true);
        btnEliminar.setDisable(false);
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(true);
    }

    public void recargarFXML(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/consultorio/vista/usuarioPersonal/medicos_asistentes.fxml"));
            Parent nuevoContenido = loader.load();
            MedicosAsistentes controller = loader.getController();
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
            List<Usuario> resultados = usuarioDB.buscarUsuario(textoBusqueda);
            tvUsuario.getItems().setAll(resultados); // Actualizar la tabla con los resultados
        }else getUsuario();
    }
}
