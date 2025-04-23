package com.consultorio.controlador.registrar;

import com.consultorio.controlador.PanelControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;

public class RegistrarCentroMedico {

    Rectangle2D dimensionPantalla= Screen.getPrimary().getBounds();
    @FXML
    Button btnRegistrarCentroMedico;

    public Connection connection;
    public Stage stage;


    //obtener conector
    public void setConector(Connection connection){

        this.connection=connection;
        System.out.println("Conector en "+ this);
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public RegistrarCentroMedico(){

    }


    public void actionBtnRegistrarCentroMedico() throws IOException {

        cargarPanelControl();
    }

    public void cargarPanelControl() throws IOException {
        //Nos conectamos a un archivo llamado FXML llamado panel_control.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/consultorio/vista/panel_control.fxml"));
        Parent root = loader.load();//cargamos

        PanelControl panelControl = loader.getController();
        panelControl.setConector(connection);


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
