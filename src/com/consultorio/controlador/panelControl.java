package com.consultorio.controlador;

import com.consultorio.util.designAll;
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

    //dimension menu izquierda para cualquier cosa que necesite la dimension
    double dimensionMenuIzquierdo= dimensionPantalla.getWidth()/4;

    //cadenas de arreglos de subMenu
    public String[] cadenaSubMenu;

    @FXML
    private VBox vbMenuIzquierdo;

    @FXML
    private ImageView ivImageMenu;
//menu boton
    @FXML
    private Button bInicio;
    @FXML
    private Button bCerraSesion;
    @FXML
    private Button bPacientes;
    @FXML
    private Button bConsultasMedicas;
    @FXML
    private Button bInventario;
    @FXML
    private Button bPagoFacturacion;
    @FXML
    private Button bReportes;
    @FXML
    private Button bUsuarioPersonal;
    @FXML
    private Button bConfiguracion;
    @FXML
    private Button bCitas;


    @FXML
    private HBox hbestructuraPanel;

    @FXML
    private StackPane spPanel;

    @FXML
    private AnchorPane apPanelDinamico;

    @FXML
    private VBox vbSubMenu;

    int categoria=0;
    designAll design;

    public panelControl(){
    this.categoria=categoria;
    //aqui sera llamado desde la dataBase

        design = new designAll(categoria);
    }




    @FXML
    public void initialize() {


        ajustesDinamicos();


        // Aqui agregare un diseño rapido para identificar limites de objetos, no sera lo ultimo

      vbMenuIzquierdo.setStyle("-fx-background-color: #000b4b;");
      apPanelDinamico.setStyle("-fx-background-color: #919df1; -fx-border-color: #101c8a;");
       vbSubMenu.setStyle("-fx-background-color: rgba(16,28,138,0.5);");

        Image image = new Image("file:/C:/Users/Alexis/Pictures/foto.jpg");
        ivImageMenu.setImage(image);





    }

    @FXML

//presionar ACCION al presionar boton
    //accion secundaria simplificacion

    public  void imprimirSubMenu(String[] cadenaSubMenu){
        for(int i=0;i<cadenaSubMenu.length;i++) {

            javafx.scene.control.Label label =  new javafx.scene.control.Label(cadenaSubMenu[i]);
            design.getDesignLabel(label);
            vbSubMenu.getChildren().add(label);
        }
    }
//ACCION DE BOTONES
    public void actionInicio(ActionEvent event) {
        // bInicio.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff;"); // Rojo con texto blanco

    }
    @FXML
    public void actionPacientes(ActionEvent event) {
        cadenaSubMenu= new String[]{"Registrar Nuevo Paciente",
                "Buscar / editar pacientes",
                "Historial clínico"};
        vbSubMenu.getChildren().clear();

        imprimirSubMenu(cadenaSubMenu);


    }

    @FXML
    public void actionCitas(ActionEvent event) {
        cadenaSubMenu = new String[]{"Agendar nueva cita", "Ver calendario / agenda del día", "Citas pendientes", "Citas pasadas"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionConsultasMedicas(ActionEvent event) {
        cadenaSubMenu = new String[]{"Iniciar consulta", "Registrar síntomas, diagnóstico, tratamiento", "Prescripción electrónica"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionInventario(ActionEvent event) {
        cadenaSubMenu = new String[]{"Medicamentos disponibles", "Registro de insumos", "Control de stock"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionPagoFacturacion(ActionEvent event) {
        cadenaSubMenu = new String[]{"Registrar pagos", "Historial de pagos por paciente", "Facturación"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionReporte(ActionEvent event) {
        cadenaSubMenu = new String[]{"Reportes de atención médica", "Reportes financieros", "Exportar datos"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionUsuarioPersonal(ActionEvent event) {
        cadenaSubMenu = new String[]{"Médicos y asistentes", "Roles y permisos"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionConfiguracion(ActionEvent event) {
        cadenaSubMenu = new String[]{"Datos del consultorio", "Preferencias del sistema", "Notificaciones / alertas"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionCerrarSesion(ActionEvent event) {
        cadenaSubMenu = new String[]{"Salir del sistema"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }


    //Menu general
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
    //ajustes dinamicos menuIzquierda

    public void ajustedMenuIzquierdaItems(){
        //estructura boton




    }

    //ajustes dinamicos subMenu
    public void ajustesSubMenu(){

    }



public void ajustesDinamicos(){
        ajustesMenus();

}


}
