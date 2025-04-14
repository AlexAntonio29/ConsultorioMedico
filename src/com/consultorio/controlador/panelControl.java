package com.consultorio.controlador;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
//import java.awt.event.ActionEvent;
import java.awt.*;
import java.beans.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;




public class panelControl {
    Screen screen = Screen.getPrimary();
    Rectangle2D dimensionPantalla = screen.getVisualBounds();


    @FXML
    private VBox vbMenuIzquierdo;

    @FXML
    private ImageView ivImageMenu;

    @FXML
    private Button bInicio;

    @FXML
    private HBox hbestructuraPanel;

    @FXML
    private StackPane spPanel;

    @FXML
    private AnchorPane apPanelDinamico;
    public panelControl(){


    }


    @FXML
    public void initialize() {

        //agregar tamaño altura a menu


        //aqui van todos los eventos al iniciar
        /*
        bInicio.setOnAction(actionEvent ->  {

        });*/
        //estrucurando dimensiones ESTO NO TOCAR
        spPanel.setPrefHeight(dimensionPantalla.getHeight());
        spPanel.setPrefWidth(dimensionPantalla.getWidth());

        hbestructuraPanel.setPrefHeight(dimensionPantalla.getHeight());
        hbestructuraPanel.setPrefWidth(dimensionPantalla.getWidth());

        vbMenuIzquierdo.setPrefHeight(dimensionPantalla.getHeight());

        apPanelDinamico.setPrefHeight(dimensionPantalla.getHeight());
        apPanelDinamico.setPrefWidth(dimensionPantalla.getWidth()-250);

        // Aqui agregare un diseño rapido para identificar limites de objetos, no sera lo ultimo

      vbMenuIzquierdo.setStyle("-fx-background-color: #000b4b;");
      apPanelDinamico.setStyle("-fx-background-color: #4bcac4; -fx-border-color: #ff0000;");

        Image image = new Image("file:/C:/Users/Alexis/Pictures/foto.jpg");
        ivImageMenu.setImage(image);





    }

    @FXML
    public void actionInicio(ActionEvent event) {
        bInicio.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff;"); // Rojo con texto blanco

    }
}
