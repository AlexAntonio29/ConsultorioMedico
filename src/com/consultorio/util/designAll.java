package com.consultorio.util;

import javafx.scene.control.Label;
//import java.awt.event.ActionEvent;


public class designAll {

    String designWhite;


    int categoria;
    public designAll(int categoria ){//aqui debe de llevar el nombre del tipo de diseño por ejemplo 2)dark,1)white,0)clasico
        this.categoria = categoria;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }


    //boton
    public void getDesignButton(){
        switch(categoria){

                case 0:
                    //diseño clasico de boton
                     break;
                case 1:
                    //diseño white de boton
                    break;
               case 2:
                   //diseño dark de boton

                   break;
        }

    }

    public void getDesignLabel(Label label){
        switch(categoria){

            case 0:
                //diseño clasico de boton
                label.setStyle("-fx-text-fill: #ffffff;");


            case 1:
                //diseño white de boton

            case 2:
                //diseño dark de boton
                label.setStyle(
                                "-fx-text-fill: #ffffff;" +       /* Color del texto */
                                "-fx-padding: 10;" +             /* Margin interno */
                                "-fx-border-radius: 10;" +       /* Bordes redondeados */
                                "-fx-font-size: 12px;"         /* Negrita */
                );



        }



    }



}
