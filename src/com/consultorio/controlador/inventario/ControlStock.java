package com.consultorio.controlador.inventario;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class controlStock {

    //checkBox
    @FXML
    private CheckBox cbAgregar;
    @FXML
    private CheckBox cbRetirar;

    @FXML
     private ChoiceBox choiceBoxCategoria;

    @FXML
            private Button btnAgregarStock;
    @FXML
            private Button btnRetirarStock;


    ArrayList<String> lista = new ArrayList<>();

    //para el ChoiceBox

    public void addChoiceBox(){
        lista.add("Medicamentos");
        lista.add("Insumos");
        lista.add("Material Quirurjico");
        lista.add("Equipo Médico");


        choiceBoxCategoria.getItems().addAll(lista);
        choiceBoxCategoria.setValue(lista.get(0));
    }

    public void modificarCheckBox(){
        cbAgregar.setSelected(true);
    }

    public void agregarCheckBox(){
        cbRetirar.setSelected(false);
        btnRetirarStock.setDisable(false);
        btnAgregarStock.setDisable(true);

    }

    public void retirarCheckBox(){
        cbAgregar.setSelected(false);
      btnRetirarStock.setDisable(true);
        btnAgregarStock.setDisable(false);

    }

    public void  addDataDefault(){
        addChoiceBox();
        modificarCheckBox();


    }

    @FXML
    public void initialize() {

        addDataDefault();



    }

}
