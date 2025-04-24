package com.consultorio.controlador.registrar;

import com.consultorio.controlador.iniciarSesion.IniciarSesion;
import com.consultorio.modelo.estructura.CentroMedico;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.AlertaConfirmacion;
import com.consultorio.util.conection.modeloDataBase.estructura.CentroMedicoDB;
import com.consultorio.util.conection.modeloDataBase.personal.EmpleadoDB;
import com.consultorio.util.conection.modeloDataBase.personal.UsuarioDB;
import com.consultorio.util.errores.VentanaErrores;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;

public class RegistrarCentroMedico {
    AlertaConfirmacion alertaConfirmacion=new AlertaConfirmacion();
    VentanaErrores ventanaErrores = new VentanaErrores();
    String mensajeError="";
    Rectangle2D dimensionPantalla= Screen.getPrimary().getBounds();
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    Button btnRegistrarCentroMedico;

    @FXML
    TextField txtNombre;
    @FXML
    TextField txtDireccion;
    @FXML
    TextField txtTelefono;
    @FXML
    Label lbPropietario;

    public Connection connection;
    public Stage stage;
    public Usuario usuario;


    //obtener conector
    public void setConector(Connection connection){

        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }
    public void setUsuario(Usuario usuario){ this.usuario=usuario; }

    public RegistrarCentroMedico(){

    }

    @FXML
    public void initialize(){

        Platform.runLater(()->{
            cargarContenidoControladores();
        });

    }

    public void cargarContenidoControladores(){
        cargarLabelPropietario();
    }

    public void cargarLabelPropietario(){
        System.out.println(usuario.getUsuario());
        lbPropietario.setText(usuario.getUsuario());
    }



@FXML
    public void actionBtnRegistrarCentroMedico() throws IOException {
try {
    if (alertaConfirmacion.mostrarConfirmacion("Estas seguro de registrar este Propietario?")){
    registrarDatosBD();
    cargarIniciarSesion();
    }

}catch (NumberFormatException e){
    mensajeError="Error de Formato favor de corregirlo";
    ventanaErrores.ventanaErrorClasico(mensajeError);
}catch (DateTimeException e){
    mensajeError="Error al capturar fecha";
    ventanaErrores.ventanaErrorClasico(mensajeError);
}catch (NullPointerException e){
    mensajeError="No se ha seleccionado el sexo";
    ventanaErrores.ventanaErrorClasico(mensajeError);
}
catch (Exception e){
    mensajeError="Error desconocido";
    ventanaErrores.ventanaErrorClasico(mensajeError);
}




    }

    public void cargarIniciarSesion() throws IOException {
        //Nos conectamos a un archivo llamado FXML llamado panel_control.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/consultorio/vista/iniciarSesion/iniciar_sesion.fxml"));
        Parent root = loader.load();//cargamos

        IniciarSesion iniciarSesion = loader.getController();
        iniciarSesion.setConector(connection);


        //hacer que no tenga el boton de cerra, minimizar ni maximizar
       // stage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.sizeToScene();
        stage.setTitle("Iniciar Sesion");//definir titulo
        //primaryStage.setAlwaysOnTop(true);//mantenerse encima de todos

        stage.setMaximized(true);
        stage.setScene(new Scene(root));//creacion de escena
        stage.centerOnScreen();

        stage.show();//mostrar
    }

    //REGSITRAR EMPLEADO EN BASE DE DATOS

    public void registrarDatosBD(){
        registrarEmpleadoBD();
        registrarUsuarioBD();
        registrarCentroMedicoBD();
    }

    public void registrarEmpleadoBD(){
        System.out.println(usuario.getEmpleado());
        System.out.println(usuario.getEmpleado().getId());
        System.out.println(usuario.getEmpleado().getCurp());
        System.out.println(usuario.getEmpleado().getNombre());
        System.out.println(usuario.getEmpleado().getaPaterno());
        System.out.println(usuario.getEmpleado().getaMaterno());
        System.out.println(usuario.getEmpleado().getFnacimiento());
        System.out.println(usuario.getEmpleado().getDireccion());
        System.out.println(usuario.getEmpleado().getTelefono());
        System.out.println(usuario.getEmpleado().getEmail());
        System.out.println(usuario.getEmpleado().getFoto());
        System.out.println(usuario.getEmpleado().getOcupacion());
        System.out.println(usuario.getEmpleado().getEspecialidad());
        System.out.println(usuario.getEmpleado().getFecha_ingreso());
        System.out.println(usuario.getEmpleado().getEdad());
        System.out.println(usuario.getEmpleado().getSexo());
        System.out.println();
        EmpleadoDB DBEmpleado= new EmpleadoDB();
        DBEmpleado.setEmpleado(usuario.getEmpleado());

        //agregar Empleado a Base de datos

    }
    public void registrarUsuarioBD(){
        System.out.println(usuario);
        UsuarioDB DBUsuario= new UsuarioDB();
        DBUsuario.setUsuario(usuario);
    }

    public void registrarCentroMedicoBD(){

      CentroMedico cm=  cargarCentroMedico(new CentroMedico());
        CentroMedicoDB DBCentroMedico = new CentroMedicoDB();
        //agregar set
        DBCentroMedico.setCentroMedico(cm);


    }

    public CentroMedico cargarCentroMedico(CentroMedico centroMedico){
        centroMedico.setNombre(txtNombre.getText());
        centroMedico.setDireccion(txtDireccion.getText());
        centroMedico.setTelefono(txtTelefono.getText());

        //fecha actual
        LocalDate fechaActual = LocalDate.now(); // Obtener la fecha actual
        java.util.Date date = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Convertir a Date
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActualString=formato.format(date);

        try{
            centroMedico.setFecha_registro(fechaActualString);
            System.out.println(fechaActualString);
            System.out.println("Fecha agregada con exito");
        }catch (Exception e){
            System.out.println("Falla al asignar fechaActual");
        }


        centroMedico.setPropietario(usuario);

        return centroMedico;


    }


}
