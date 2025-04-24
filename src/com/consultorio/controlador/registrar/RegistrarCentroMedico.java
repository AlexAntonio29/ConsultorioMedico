package com.consultorio.controlador.registrar;

import com.consultorio.controlador.iniciarSesion.IniciarSesion;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.errores.VentanaErrores;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.time.DateTimeException;

public class RegistrarCentroMedico {
    VentanaErrores ventanaErrores = new VentanaErrores();
    String mensajeError="";
    Rectangle2D dimensionPantalla= Screen.getPrimary().getBounds();
    @FXML
    Button btnRegistrarCentroMedico;

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
    public void actionBtnRegistrarCentroMedico() throws IOException {
try {
    registrarDatosBD();
    cargarIniciarSesion();

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

    }
    public void registrarUsuarioBD(){
        System.out.println(usuario);
    }

    public void registrarCentroMedicoBD(){}
}
