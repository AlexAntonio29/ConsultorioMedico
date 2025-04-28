package com.consultorio.controlador.iniciarSesion;

import com.consultorio.controlador.PanelControl;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.conection.modeloDataBase.personal.UsuarioDB;
import com.consultorio.util.alertas.errores.VentanaErrores;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class IniciarSesion {

    VentanaErrores ventanaErrores = new VentanaErrores();
    Rectangle2D dimensionPantalla= Screen.getPrimary().getBounds();
    Stage stage;
    Connection connection;
     @FXML
     Button btnIniciarSesion;
     @FXML
    TextField tfUsuario;
     @FXML
     TextField tfPassword;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public void iniciarSesion(){
    }

    @FXML
    public void actionIniciarSesion() throws SQLException, IOException {

        Usuario usuario= cargarUsuarioDB();
        if (usuario!=null)
            if (usuario.getPassword().equals(tfPassword.getText()))
            cargarPanelControl(usuario);
        else ventanaErrores.ventanaErrorClasico("Contraseña incorrecta");
        else ventanaErrores.ventanaErrorClasico("El usuario no existe");
    tfUsuario.setText("");
    tfPassword.setText("");
    }



    public Usuario cargarUsuarioDB() throws SQLException{
        UsuarioDB usuarioDB = new UsuarioDB();
        usuarioDB.setConector(connection);
        Usuario usuario = usuarioDB.getUsuario(tfUsuario.getText());
        return usuario;

    }

    public void cargarPanelControl(Usuario usuario) throws SQLException, IOException {
        //Nos conectamos a un archivo llamado FXML llamado panel_control.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/consultorio/vista/panel_control.fxml"));
        Parent root = loader.load();//cargamos

        PanelControl panelControl = loader.getController();
        panelControl.setConector(connection);
        panelControl.setUsuario(usuario);


        //hacer que no tenga el boton de cerra, minimizar ni maximizar
        // stage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.sizeToScene();
        stage.setTitle("Panel Control");//definir titulo
        //primaryStage.setAlwaysOnTop(true);//mantenerse encima de todos

        stage.setMaximized(true);
        stage.setScene(new Scene(root));//creacion de escena
        stage.setWidth(dimensionPantalla.getWidth());
        stage.setHeight(dimensionPantalla.getHeight());
        stage.setMinWidth(dimensionPantalla.getWidth());
        stage.setMinHeight(dimensionPantalla.getHeight());
        stage.setMaxWidth(dimensionPantalla.getWidth());
        stage.setMaxHeight(dimensionPantalla.getHeight());
        stage.centerOnScreen();

        stage.show();//mostrar
    }




}
