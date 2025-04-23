package com.consultorio.controlador.registrar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;


public class RegistrarAdmin {



    @FXML
    ChoiceBox cbSexo;
    @FXML
    ChoiceBox cbEdad;

    @FXML
    Button btnIngresar;

    public Connection connection;
    public Stage stage;


    //obtener conector
    public void setConector(Connection connection){

        this.connection=connection;
        System.out.println("Conector en "+ this);
    }


    public void setStage(Stage stage){
        this.stage = stage;
    }




    //cargar datos
    public void cargarCbSexo(){


       cbSexo.setValue("Masculino");
    }

    public void cargarCbEdad(){
        ArrayList<String> lista=new ArrayList<>();
        for(int i=0;i<151;i++)
            lista.add(String.valueOf(i));
        cbEdad.getItems().addAll(lista);
        cbEdad.setValue(lista.get(0));


    }

    public void cargar(){
        cargarCbSexo();
        cargarCbEdad();
    }





    public void initialize() {
        cargar();
    }


    public void actionRegistrar() throws IOException {


        cargarRegistroCentroMedico();
    }

    public void cargarRegistroCentroMedico() throws IOException {
       try {
           FXMLLoader loader = new FXMLLoader(RegistrarCentroMedico.class.getResource("/com/consultorio/vista/registrar/registrar_centro_medico.fxml"));
           Parent root = loader.load();
           stage.setScene(new Scene(root));
           RegistrarCentroMedico controlador= loader.getController();

           controlador.setConector(connection);
           controlador.setStage(stage);

            stage.centerOnScreen();

           stage.initStyle(StageStyle.UNDECORATED);
           //primaryStage.sizeToScene();
           stage.setTitle("Registro Centro Medico");
           stage.setMaximized(true);

           stage.show();


       }catch (Exception e){
           System.out.println(e.getMessage());
       }
    }


}
