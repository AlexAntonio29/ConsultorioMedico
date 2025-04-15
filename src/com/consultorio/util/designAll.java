package com.consultorio.util;

import javafx.scene.control.Button;
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
    public void getDesignButton(Button button,int select){//1 es en estado precionado o seleccionado y 0 lo contrario
        switch(categoria){

                case 0:
                    //diseño clasico de boton
                    if(select == 1){
                        button.setStyle(
                                "-fx-background-color: #233f9c;" + /* Fondo azul oscuro para complementar */
                                        "-fx-text-fill: #ffffff;" +       /* Texto blanco para contraste */
                                        "-fx-font-size: 12px;" +         /* Tamaño de fuente elegante */
                                        "-fx-border-color: #000b4b;" +   /* Borde en el mismo color que el VBox */
                                        "-fx-border-radius: 8;" +        /* Bordes redondeados */
                                        "-fx-background-radius: 8;" +    /* Fondo con bordes redondeados */
                                        "-fx-padding: 10;"               /* Espaciado interno */
                        );
                    }
                    else if(select == 0){
                        button.setStyle(
                                "-fx-background-color: #1a2f75;" + /* Fondo azul oscuro para complementar */
                                        "-fx-text-fill: #ffffff;" +       /* Texto blanco para contraste */
                                        "-fx-font-size: 12px;" +         /* Tamaño de fuente elegante */
                                        "-fx-border-color: #000b4b;" +   /* Borde en el mismo color que el VBox */
                                        "-fx-border-radius: 8;" +        /* Bordes redondeados */
                                        "-fx-background-radius: 8;" +    /* Fondo con bordes redondeados */
                                        "-fx-padding: 10;"               /* Espaciado interno */
                        );
                    }



                     break;
                case 1:
                    //diseño white de boton
                    break;
               case 2:
                   //diseño dark de boton

                   break;
        }

    }

    public void getDesignLabel(Label label, int select){
        switch(categoria){

            case 0:
                //diseño clasico de boton
                if(select == 1){
                    label.setStyle("-fx-text-fill: #ffffff;" +       /* Color del texto */
                            "-fx-padding: 10;" +             /* Margin interno */
                            "-fx-border-radius: 10;" +       /* Bordes redondeados */
                            "-fx-underline: true;" +
                            "-fx-font-size: 12px;"  );

                }
                else if(select == 0){
                    label.setStyle("-fx-text-fill: #e1e1e1;" +       /* Color del texto */
                            "-fx-padding: 8;" +             /* Margin interno */
                            "-fx-border-radius: 10;" +       /* Bordes redondeados */
                            "-fx-underline: false;" +
                            "-fx-font-size: 12px;"  );
                }


                break;

            case 1:
                //diseño white de boton

                break;

            case 2:
                //diseño dark de boton
                label.setStyle(
                                "-fx-text-fill: #ffffff;" +       /* Color del texto */
                                "-fx-padding: 10;" +             /* Margin interno */
                                "-fx-border-radius: 10;" +       /* Bordes redondeados */
                                "-fx-font-size: 12px;"         /* Negrita */
                );
                break;



        }



    }



}
