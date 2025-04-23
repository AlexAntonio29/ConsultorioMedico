package com.consultorio.controlador.configuracion;

import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.util.Objects;


public class Perfil {

    String dirImage="";

    @FXML
    ImageView ivFoto;

    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }



    @FXML
    public void initialize() {
    cargarDatos();



    }

    //metodos de ejecucion

    public void cargarFoto(){
        //llamar metodo DataBase
        dirImage=getDataBase(dirImage);


        if (Objects.equals(dirImage, "")) dirImage="/resource/img/user_unknown.jpg";


        ivFoto.setImage(new Image(getClass().getResource(dirImage).toExternalForm()));

        ivFoto.setFitWidth(100); // Ancho máximo
        ivFoto.setFitHeight(100); // Altura máxima
        ivFoto.setPreserveRatio(true); // Mantener la proporción
        ivFoto.setSmooth(true); // Suavizado para bordes más limpios
        ivFoto.setCache(true);
    }

    public void cargarDatos(){
        cargarFoto();
    }


    //Metodo DataBase
    public String getDataBase(String typeData){

        //llamar a la Base de datos para proporcionar direccion
        String getData="";

        return  getData;
    }

    public void setDataBase(String dirImage){}

}
