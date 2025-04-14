package com.consultorio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {


        //obtener la dimension de la pantalla cualquiera
        Screen screen = Screen.getPrimary();
        Rectangle2D dimensionPantalla = screen.getVisualBounds();

        //Nos conectamos a un archivo llamado FXML llamado panel_control.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("util/panel_control.fxml"));
        Parent root = loader.load();//cargamos

        //hacer que no tenga el boton de cerra, minimizar ni maximizar
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.sizeToScene();
        primaryStage.setTitle("Panel Control");//definir titulo
        primaryStage.setScene(new Scene(root));//creacion de escena
        primaryStage.setWidth(dimensionPantalla.getWidth());
        primaryStage.setHeight(dimensionPantalla.getHeight());
        primaryStage.setMinWidth(dimensionPantalla.getWidth());
        primaryStage.setMinHeight(dimensionPantalla.getHeight());
        primaryStage.setMaxWidth(dimensionPantalla.getWidth());
        primaryStage.setMaxHeight(dimensionPantalla.getHeight());


        primaryStage.show();//mostrar

    }


    public static void main(String[] args) {
        launch(args);
        //hola
    }
}

