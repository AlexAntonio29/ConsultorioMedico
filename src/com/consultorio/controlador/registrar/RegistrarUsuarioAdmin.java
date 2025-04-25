package com.consultorio.controlador.registrar;

import com.consultorio.modelo.personal.Empleado;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.errores.VentanaErrores;
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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.time.DateTimeException;

public class RegistrarUsuarioAdmin {
    Rectangle2D dimensionPantalla= Screen.getPrimary().getBounds();
    public Connection connection;
    public Stage stage;
    VentanaErrores ventanaErrores = new VentanaErrores();
    String mensajeError="";

    //agregar empleado
    public Empleado empleado;
    @FXML
    Label lbUsuario;
    @FXML
    TextField tfPassword;
    @FXML
    TextField tfPasswordSeguridad;




    @FXML
    Button btnRegistrarCentroMedico;

//      METODOS QUE LLAMAN A OBJETOS
    //obtener conector

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public void setConector(Connection connection){

        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }


@FXML
    public void actionBtnRegistrarCentroMedico() throws IOException {

try{
    if (verificarPassword(tfPassword.getText(),tfPasswordSeguridad.getText())) {
        cargarCentroMedico();
    System.out.println("Cargando centro medico"); }
    else ventanaErrores.ventanaErrorClasico("Contraseña no coincide");
}
catch (NumberFormatException e){
    mensajeError="Error de Formato favor de corregirlo";
    ventanaErrores.ventanaErrorClasico(mensajeError);
}catch (DateTimeException e){
    mensajeError="Error al capturar fecha";
    ventanaErrores.ventanaErrorClasico(mensajeError);
}catch (NullPointerException e){
    mensajeError="No se ha seleccionado el sexo";
    ventanaErrores.ventanaErrorClasico(mensajeError);
}catch (IllegalStateException e){
    mensajeError="Error tensional ya que es para volver a repe";
    ventanaErrores.ventanaErrorClasico(mensajeError);
}
catch (Exception e){
    mensajeError="Error desconocido";
    ventanaErrores.ventanaErrorClasico(mensajeError);
}


    }


    public void cargarCentroMedico() throws IOException {
       try {
           FXMLLoader loader = new FXMLLoader(RegistrarCentroMedico.class.getResource("/com/consultorio/vista/registrar/registrar_centro_medico.fxml"));
           Parent root = loader.load();
           stage.setScene(new Scene(root));
           RegistrarCentroMedico controlador= loader.getController();

           controlador.setConector(connection);
           controlador.setStage(stage);
           controlador.setUsuario(cargarUsuario(new Usuario()));
           stage.centerOnScreen();
           stage.setTitle("Registro Centro Medico");
           //stage.initStyle(StageStyle.UNDECORATED);
           // stage.sizeToScene();

           //stage.setMaximized(true);

           //stage.show();

       }catch (Exception e){
           ventanaErrores.ventanaErrorClasico("Error al cargar componentes");
       }
    }
    //cargar usuario

    public Usuario cargarUsuario(Usuario usuario) throws IOException {

        System.out.println(empleado);

            usuario.setId("1");
            usuario.setUsuario(lbUsuario.getText());
            usuario.setPassword(tfPassword.getText());
            usuario.setEmpleado(empleado);
            return usuario;


    }

    public boolean verificarPassword(String password, String passwordSeguridad){
        if(password.equals(passwordSeguridad))return true;
        else return false;

    }
}
