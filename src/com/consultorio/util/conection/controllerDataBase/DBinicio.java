package com.consultorio.util.conection.controllerDataBase;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;


public class DBinicio {

    Screen screen = Screen.getPrimary();
    Rectangle2D dimensionPantalla = screen.getVisualBounds();
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane anchorPane;
    public DBinicio(){

    }

    //ajustarPosicion

    public void posicionItems(){

        double anchoCentrado = (anchorPane.getWidth()/2)-(grid.getWidth()/2);
        double altoCentrado = (anchorPane.getHeight()/2)-(grid.getHeight()/2);

        //grid.setTranslateX(anchoCentrado);



    }

    @FXML
    public void initialize(){
        Platform.runLater(this::posicionItems);

    }
}
