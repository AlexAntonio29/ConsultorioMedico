package com.consultorio.controlador;


import com.consultorio.util.designAll;
import com.consultorio.util.alertaConfirmacion;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
//import java.awt.event.ActionEvent;
import java.util.Objects;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;




public class panelControl {

    //archivos
    String dirImage="";



    Screen screen = Screen.getPrimary();
    Rectangle2D dimensionPantalla = screen.getVisualBounds();

    //Crear una confirmacion Alerta

    alertaConfirmacion alerta = new alertaConfirmacion();

    //dimension menu izquierda para cualquier cosa que necesite la dimension
    double dimensionMenuIzquierdo= dimensionPantalla.getWidth()/4;

    //cadenas de arreglos de subMenu
    public String[] cadenaSubMenu;

    //Items de AnchorPanelDinamico
    private Parent contenidoAnchorPanelDinamico;

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

    //label Usuario

    @FXML
            private Label lbNameUser;

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

        cargarRecursos();
        ajustesDinamicos();
        ajustesDiseno();


        // Aqui agregare un diseño rapido para identificar limites de objetos, no sera lo ultimo

      vbMenuIzquierdo.setStyle("-fx-background-color: #000b4b;");
      apPanelDinamico.setStyle("-fx-background-color: #ffffff; -fx-border-color: #101c8a;");
       vbSubMenu.setStyle("-fx-background-color: rgba(16,28,138,0.5);");

       //limpiar AnchorPanel Dinamico Y cargar FXML







    }

    //CARGAR RECURSOS
    public void cargarRecursos(){


        loadImage();

        vbSubMenu.setVisible(false);
        //direccion de inicio por defecto
        String rutaDefectoInicio="/com/consultorio/vista/inicio.fxml";
        updateContenidoAnchorPane(rutaDefectoInicio);

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



    }
//LLAMADO DE DATABASE


    //LLAMADO IMAGEN
    public void loadImage(){
        //llamar metodo DataBase
        dirImage=getDataBase(dirImage);


        if (Objects.equals(dirImage, "")) dirImage="/resource/img/user_unknown.jpg";


        ivImageMenu.setImage(new Image(getClass().getResource(dirImage).toExternalForm()));


    }


//LLAMADO A FXML

    public void cargarFXML(String cadena){
        //cargar el fxml
        //usare un switch para distribuir
        switch (cadena) {
            case "Inicio/DashBoard":
                System.out.println("Acción: Ir a inicio");
                updateContenidoAnchorPane("/com/consultorio/vista/inicio.fxml");
                break;
            // Pacientes
            case "Registrar Nuevo Paciente":
                System.out.println("Acción: Registrar nuevo paciente");
                updateContenidoAnchorPane("/com/consultorio/vista/pacientes/registrar_nuevo_paciente.fxml");
                break;
            case "Buscar / editar pacientes":
                System.out.println("Acción: Buscar / editar pacientes");
                updateContenidoAnchorPane("/com/consultorio/vista/pacientes/buscar_editar_paciente.fxml");
                break;
            case "Historial clínico":
                System.out.println("Acción: Ver historial clínico");
                updateContenidoAnchorPane("/com/consultorio/vista/pacientes/historial_clinico.fxml");
                break;
            case "Adjuntar archivos (estudios, recetas, etc.)":
                System.out.println("Acción: Adjuntar archivos");
                break;

            // Citas
            case "Agendar nueva cita":
                System.out.println("Acción: Agendar nueva cita");
                updateContenidoAnchorPane("/com/consultorio/vista/citas/agendar_nueva_cita.fxml");

                break;
            case "Ver calendario / agenda del día":
                System.out.println("Acción: Ver calendario / agenda del día");
                updateContenidoAnchorPane("/com/consultorio/vista/citas/agenda_dia.fxml");
                break;
            case "Citas pendientes":
                System.out.println("Acción: Ver citas pendientes");
                updateContenidoAnchorPane("/com/consultorio/vista/citas/citas_pendientes.fxml");
                break;
            case "Citas pasadas":
                System.out.println("Acción: Ver citas pasadas");
                updateContenidoAnchorPane("/com/consultorio/vista/citas/citas_pasadas.fxml");
                break;

            // Consultas Médicas
            case "Iniciar consulta":
                System.out.println("Acción: Iniciar consulta médica");
                updateContenidoAnchorPane("/com/consultorio/vista/consultasMedicas/iniciar_consulta.fxml");

                break;
            case "Registrar síntomas, diagnóstico, tratamiento":

                System.out.println("Acción: Registrar detalles de consulta médica");
                updateContenidoAnchorPane("/com/consultorio/vista/consultasMedicas/registrar_diagnostico_tratamiento_sintomas.fxml");

                break;
            case "Prescripción electrónica":
                System.out.println("Acción: Emitir prescripción electrónica");
                updateContenidoAnchorPane("/com/consultorio/vista/consultasMedicas/prescripcion_electronica.fxml");

                break;

            // Inventario / Farmacia
            case "Medicamentos disponibles":
                System.out.println("Acción: Consultar medicamentos disponibles");
                updateContenidoAnchorPane("/com/consultorio/vista/inventario/medicamentos_disponibles.fxml");


                break;
            case "Registro de insumos":
                System.out.println("Acción: Registrar insumos");
                updateContenidoAnchorPane("/com/consultorio/vista/inventario/registro_insumos.fxml");

                break;
            case "Control de stock":
                System.out.println("Acción: Control de stock");
                updateContenidoAnchorPane("/com/consultorio/vista/inventario/control_stock.fxml");

                break;

            // Pagos y Facturación
            case "Registrar pagos":
                System.out.println("Acción: Registrar pagos de pacientes");
                updateContenidoAnchorPane("/com/consultorio/vista/pagoYfacturacion/registrar_pagos.fxml");
                break;
            case "Historial de pagos por paciente":
                System.out.println("Acción: Ver historial de pagos por paciente");
                updateContenidoAnchorPane("/com/consultorio/vista/pagoYfacturacion/historial_pagos_pacientes.fxml");
                break;
            case "Facturación":
                System.out.println("Acción: Realizar facturación");
                updateContenidoAnchorPane("/com/consultorio/vista/pagoYfacturacion/facturacion.fxml");
                break;

            // Reportes
            case "Reportes de atención médica":
                System.out.println("Acción: Generar reportes de atención médica");
                updateContenidoAnchorPane("/com/consultorio/vista/reportes/reportes_atencion_medica.fxml");
                break;
            case "Reportes financieros":
                System.out.println("Acción: Generar reportes financieros");
                updateContenidoAnchorPane("/com/consultorio/vista/reportes/reportes_financieros.fxml");
                break;
            case "Exportar datos":
                System.out.println("Acción: Exportar datos del sistema");
                updateContenidoAnchorPane("/com/consultorio/vista/reportes/exportar_datos.fxml");
                break;

            // Usuarios / Personal
            case "Personal general":
                System.out.println("Acción: Personal general");
                //updateContenidoAnchorPane("/com/consultorio/vista/usuarioPersonal/medicos_asistentes.fxml");
                break;
            case "Médicos y asistentes":
                System.out.println("Acción: Administrar médicos y asistentes");
                updateContenidoAnchorPane("/com/consultorio/vista/usuarioPersonal/medicos_asistentes.fxml");
                break;
            case "Roles y permisos":
                System.out.println("Acción: Configurar roles y permisos");
                updateContenidoAnchorPane("/com/consultorio/vista/usuarioPersonal/roles_permisos.fxml");
                break;

            // Configuración
            case "Datos del consultorio":
                System.out.println("Acción: Configurar datos del consultorio");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracion/datos_consultorio.fxml");
                break;
            case "Preferencias del sistema":
                System.out.println("Acción: Configurar preferencias del sistema");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracion/preferencias_sistema.fxml");
                break;
            case "Notificaciones / alertas":
                System.out.println("Acción: Configurar notificaciones y alertas");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracion/notificaciones_alerta.fxml");
                break;
            case "Perfil":
                System.out.println("Acción: Configurar notificaciones y alertas");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracion/perfil.fxml");
                break;

            // Cerrar sesión / Salir
            case "Salir del sistema":
                System.out.println("Acción: Cerrar sesión y salir");
                if (alerta.mostrarConfirmacion("¿Deseas Salir del sistema?")) Platform.exit();

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
                vbSubMenu.setVisible(false);
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

    public void updateContenidoAnchorPane(String ruta){
        try {

            apPanelDinamico.getChildren().clear();
            contenidoAnchorPanelDinamico= FXMLLoader.load((getClass().getResource(ruta)));
            apPanelDinamico.getChildren().add(contenidoAnchorPanelDinamico);

            // Ajustar para que se adapte al tamaño del AnchorPane
            AnchorPane.setTopAnchor(contenidoAnchorPanelDinamico, 0.0);
            AnchorPane.setBottomAnchor(contenidoAnchorPanelDinamico, 0.0);
            AnchorPane.setLeftAnchor(contenidoAnchorPanelDinamico, 0.0);
            AnchorPane.setRightAnchor(contenidoAnchorPanelDinamico, 0.0);
        }catch (Exception e){
            System.out.println("ERROR AL CARGAR FXML "+e.getMessage()+"  ERROR: "+ e);
        }

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
    public void actionPushInicio() {
        design.getDesignButton(bInicio,1);
        cargarFXML(bInicio.getText());

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
        cadenaSubMenu = new String[]{"Personal general","Médicos y asistentes", "Roles y permisos"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionConfiguracion() {
        design.getDesignButton(bConfiguracion,1);
        cadenaSubMenu = new String[]{"Perfil","Datos del consultorio", "Preferencias del sistema", "Notificaciones / alertas"};
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






    //ajustes dinamicos menuIzquierda

    public void ajustedMenuIzquierdaItems(){
        //estructura boton




    }

    //ajustes dinamicos subMenu
    public void ajustesSubMenu(){

    }


    //AJUSTES DISENO
    public void ajustesDiseno(){

        imageDesign();
        labelDesign();

    }

    //Diseño image
    public void imageDesign(){

    }
    //Diseño labe
    public void labelDesign(){
        design.getDesignLabel(lbNameUser,0);
    }






    public void ajustesDinamicos(){
        ajustesMenus();


}
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

        //Ajuste Dinamico Imagen
        double anchoMenuCentrado= vbMenuIzquierdo.getPrefWidth()/2;
        double anchoImagenCentrado=ivImageMenu.getFitWidth()/2;
        double centralizadoImagen= anchoMenuCentrado-anchoImagenCentrado;
        ivImageMenu.setTranslateX(centralizadoImagen);



        //posicionar subMenu
        double puntoInicialSubMenu=-((dimensionPantalla.getWidth()/2)-(dimensionSubMenuIzquierdo/2));
        double posicionSubMenu= puntoInicialSubMenu+dimensionMenuIzquierdo;
        vbSubMenu.setTranslateX(posicionSubMenu);

        //ajustes dinamicos label User

       // double anchoLabelUserCentralizado=lbNameUser.getPrefWidth()/2;
       // double centralizadoLabel= anchoMenuCentrado-anchoLabelUserCentralizado;
        lbNameUser.setTranslateX(centralizadoImagen);

    }
//----------------------------------------------------------------------

//Cargar imagen



    //LLAMAR DATABASE
    public String getDataBase(String typeData){

        //llamar a la Base de datos para proporcionar direccion
        String getData="";

        return  getData;
    }

    public void setDataBase(String dirImage){}

}
