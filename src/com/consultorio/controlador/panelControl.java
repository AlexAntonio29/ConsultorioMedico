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



//INICIAR EL FXML PANEL CONTROL
    @FXML
    public void initialize() {


        ajustesDinamicos();
        vbSubMenu.setVisible(false);


        // Aqui agregare un diseño rapido para identificar limites de objetos, no sera lo ultimo

      vbMenuIzquierdo.setStyle("-fx-background-color: #000b4b;");
      apPanelDinamico.setStyle("-fx-background-color: #919df1; -fx-border-color: #101c8a;");
       vbSubMenu.setStyle("-fx-background-color: rgba(16,28,138,0.5);");


       //agregar diseño a los botones

           design.getDesignButton(bInicio,0);
           design.getDesignButton(bPacientes,0);
           design.getDesignButton(bCitas,0);
           design.getDesignButton(bReportes,0);
           design.getDesignButton(bUsuarioPersonal,0);
           design.getDesignButton(bConsultasMedicas,0);
           design.getDesignButton(bInventario,0);
           design.getDesignButton(bPagoFacturacion,0);
          design.getDesignButton(bConfiguracion,0);
          design.getDesignButton(bCerraSesion,0);


        Image image = new Image("file:/C:/Users/Alexis/Pictures/foto.jpg");
        ivImageMenu.setImage(image);





    }


//LLAMADO A FXML

    public void cargarFXML(String cadena){//cargar el fxml
        //usare un switch para distribuir
        switch (cadena) {
            // Pacientes
            case "Registrar Nuevo Paciente":
                System.out.println("Acción: Registrar nuevo paciente");
                break;
            case "Buscar / editar pacientes":
                System.out.println("Acción: Buscar / editar pacientes");
                break;
            case "Historial clínico":
                System.out.println("Acción: Ver historial clínico");
                break;
            case "Adjuntar archivos (estudios, recetas, etc.)":
                System.out.println("Acción: Adjuntar archivos");
                break;

            // Citas
            case "Agendar nueva cita":
                System.out.println("Acción: Agendar nueva cita");
                break;
            case "Ver calendario / agenda del día":
                System.out.println("Acción: Ver calendario / agenda del día");
                break;
            case "Citas pendientes":
                System.out.println("Acción: Ver citas pendientes");
                break;
            case "Citas pasadas":
                System.out.println("Acción: Ver citas pasadas");
                break;

            // Consultas Médicas
            case "Iniciar consulta":
                System.out.println("Acción: Iniciar consulta médica");
                break;
            case "Registrar síntomas, diagnóstico, tratamiento":
                System.out.println("Acción: Registrar detalles de consulta médica");
                break;
            case "Prescripción electrónica":
                System.out.println("Acción: Emitir prescripción electrónica");
                break;

            // Inventario / Farmacia
            case "Medicamentos disponibles":
                System.out.println("Acción: Consultar medicamentos disponibles");
                break;
            case "Registro de insumos":
                System.out.println("Acción: Registrar insumos");
                break;
            case "Control de stock":
                System.out.println("Acción: Control de stock");
                break;

            // Pagos y Facturación
            case "Registrar pagos":
                System.out.println("Acción: Registrar pagos de pacientes");
                break;
            case "Historial de pagos por paciente":
                System.out.println("Acción: Ver historial de pagos por paciente");
                break;
            case "Facturación":
                System.out.println("Acción: Realizar facturación");
                break;

            // Reportes
            case "Reportes de atención médica":
                System.out.println("Acción: Generar reportes de atención médica");
                break;
            case "Reportes financieros":
                System.out.println("Acción: Generar reportes financieros");
                break;
            case "Exportar datos":
                System.out.println("Acción: Exportar datos del sistema");
                break;

            // Usuarios / Personal
            case "Médicos y asistentes":
                System.out.println("Acción: Administrar médicos y asistentes");
                break;
            case "Roles y permisos":
                System.out.println("Acción: Configurar roles y permisos");
                break;

            // Configuración
            case "Datos del consultorio":
                System.out.println("Acción: Configurar datos del consultorio");
                break;
            case "Preferencias del sistema":
                System.out.println("Acción: Configurar preferencias del sistema");
                break;
            case "Notificaciones / alertas":
                System.out.println("Acción: Configurar notificaciones y alertas");
                break;

            // Cerrar sesión / Salir
            case "Salir del sistema":
                System.out.println("Acción: Cerrar sesión y salir");
                break;

            default:
                System.out.println("Subtema no reconocido");
                break;
        }

    }

    //ACCION SECUNDARIAS ---------------------------------

    @FXML
    public  void imprimirSubMenu(String[] cadenaSubMenu){

        vbSubMenu.setVisible(true);

        for(int i=0;i<cadenaSubMenu.length;i++) {

            javafx.scene.control.Label label =  new javafx.scene.control.Label(cadenaSubMenu[i]);
            design.getDesignLabel(label,0);

            label.setOnMouseClicked(event ->{
                cargarFXML(label.getText());
                design.getDesignLabel(label,1);
            });

           label.setOnMouseEntered(event ->{
               design.getDesignLabel(label,1);
           });
           label.setOnMouseExited(event->{
               design.getDesignLabel(label,0);
           });

            vbSubMenu.getChildren().add(label);


        }
    }

    @FXML
    public void ocultarSubmenu(){
        vbSubMenu.setVisible(false);
        design.getDesignButton(bInicio,0);
        design.getDesignButton(bPacientes,0);
        design.getDesignButton(bCitas,0);
        design.getDesignButton(bReportes,0);
        design.getDesignButton(bUsuarioPersonal,0);
        design.getDesignButton(bConsultasMedicas,0);
        design.getDesignButton(bInventario,0);
        design.getDesignButton(bPagoFacturacion,0);
        design.getDesignButton(bConfiguracion,0);
        design.getDesignButton(bCerraSesion,0);
    }

    public void mostrarSubmenu(){
        vbSubMenu.setVisible(true);
    }





//------------------------------------------------------------


//presionar ACCION al presionar boton


//ACCION DE BOTONES

    //--------------------------------------------------------------
    @FXML
    public void actionInicio() {
        design.getDesignButton(bInicio,1);
        // bInicio.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff;"); // Rojo con texto blanco

    }

    @FXML
    public void actionPacientes() {
        design.getDesignButton(bPacientes,1);
        cadenaSubMenu= new String[]{"Registrar Nuevo Paciente",
                "Buscar / editar pacientes",
                "Historial clínico"};
        vbSubMenu.getChildren().clear();


        imprimirSubMenu(cadenaSubMenu);




    }

    @FXML
    public void actionCitas() {
        design.getDesignButton(bCitas,1);
        cadenaSubMenu = new String[]{"Agendar nueva cita", "Ver calendario / agenda del día", "Citas pendientes", "Citas pasadas"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionConsultasMedicas() {
        design.getDesignButton(bConsultasMedicas,1);
        cadenaSubMenu = new String[]{"Iniciar consulta", "Registrar síntomas, diagnóstico, tratamiento", "Prescripción electrónica"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionInventario() {
        design.getDesignButton(bInventario,1);
        cadenaSubMenu = new String[]{"Medicamentos disponibles", "Registro de insumos", "Control de stock"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionPagoFacturacion() {
        design.getDesignButton(bPagoFacturacion,1);
        cadenaSubMenu = new String[]{"Registrar pagos", "Historial de pagos por paciente", "Facturación"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionReporte() {
        design.getDesignButton(bReportes,1);
        cadenaSubMenu = new String[]{"Reportes de atención médica", "Reportes financieros", "Exportar datos"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionUsuarioPersonal() {
        design.getDesignButton(bUsuarioPersonal,1);
        cadenaSubMenu = new String[]{"Médicos y asistentes", "Roles y permisos"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionConfiguracion() {
        design.getDesignButton(bConfiguracion,1);
        cadenaSubMenu = new String[]{"Datos del consultorio", "Preferencias del sistema", "Notificaciones / alertas"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionCerrarSesion() {
        design.getDesignButton(bCerraSesion,1);
        cadenaSubMenu = new String[]{"Salir del sistema"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

//----------------------------------------------------------------------





    //MENU GENERAL
    //-----------------------------------------------------------
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
//----------------------------------------------------------------------





}
