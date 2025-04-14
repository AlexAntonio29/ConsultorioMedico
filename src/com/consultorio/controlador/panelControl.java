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

    @FXML
    private VBox vbSubMenu;
    public panelControl(){


    }




    @FXML
    public void initialize() {


        ajustesDinamicos();


        // Aqui agregare un diseño rapido para identificar limites de objetos, no sera lo ultimo

      vbMenuIzquierdo.setStyle("-fx-background-color: #000b4b;");
      apPanelDinamico.setStyle("-fx-background-color: #4bcac4; -fx-border-color: #ff0000;");
       vbSubMenu.setStyle("-fx-background-color: #8c4040;");

        Image image = new Image("file:/C:/Users/Alexis/Pictures/foto.jpg");
        ivImageMenu.setImage(image);





    }

    @FXML
    public void actionInicio(ActionEvent event) {
        bInicio.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff;"); // Rojo con texto blanco

    }

    public void ajustesMenus(){
        //estrucurando dimensiones ESTO NO TOCAR
        //Dimension del Stack Pane
        spPanel.setPrefHeight(dimensionPantalla.getHeight());
        spPanel.setPrefWidth(dimensionPantalla.getWidth());

        //Dimension del Horizontal Pane
        hbestructuraPanel.setPrefHeight(dimensionPantalla.getHeight());
        hbestructuraPanel.setPrefWidth(dimensionPantalla.getWidth());

        //Dimension del Vertical Pane
        vbMenuIzquierdo.setPrefHeight(dimensionPantalla.getHeight());
        //Dimension del anchor Pane
        apPanelDinamico.setPrefHeight(dimensionPantalla.getHeight());
        apPanelDinamico.setPrefWidth(dimensionPantalla.getWidth());
        //Dimension del menuIzquierdo
        double dimensionMenuIzquierdo= dimensionPantalla.getWidth()/4;
        vbMenuIzquierdo.setMaxWidth(dimensionMenuIzquierdo);
        vbMenuIzquierdo.setMaxHeight(dimensionPantalla.getHeight());
        vbMenuIzquierdo.setPrefWidth(dimensionMenuIzquierdo);
        vbMenuIzquierdo.setPrefHeight(dimensionPantalla.getHeight());
        vbMenuIzquierdo.setMinWidth(dimensionMenuIzquierdo);
        vbMenuIzquierdo.setMinHeight(dimensionPantalla.getHeight());
        //Dimension de SubMenu

        double dimensionSubMenuIzquierdo= dimensionPantalla.getWidth()/4;
        vbSubMenu.setMaxWidth(dimensionSubMenuIzquierdo);
        vbSubMenu.setMaxHeight(dimensionPantalla.getHeight());
        vbSubMenu.setPrefWidth(dimensionSubMenuIzquierdo);
        vbSubMenu.setPrefHeight(dimensionPantalla.getHeight());
        vbSubMenu.setMinWidth(dimensionSubMenuIzquierdo);
        vbSubMenu.setMinHeight(dimensionPantalla.getHeight());



        //posicionar subMenu
        double puntoInicialSubMenu=-((dimensionPantalla.getWidth()/2)-(dimensionSubMenuIzquierdo/2));
        double posicionSubMenu= puntoInicialSubMenu+dimensionMenuIzquierdo;
        vbSubMenu.setTranslateX(posicionSubMenu);

    }

    
public void ajustesDinamicos(){
        ajustesMenus();

}


}
