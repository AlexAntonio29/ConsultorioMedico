package com.consultorio.controlador;


import com.consultorio.controlador.cerrarSesion.CerrarSesion;
import com.consultorio.controlador.cerrarSesion.SalirSistema;
import com.consultorio.controlador.citas.AgendaDia;
import com.consultorio.controlador.citas.AgendarNuevaCita;
import com.consultorio.controlador.citas.CitasPasadas;
import com.consultorio.controlador.citas.CitasPendientes;
import com.consultorio.controlador.configuracion.DatosConsultorio;
import com.consultorio.controlador.configuracion.NotificacionAlerta;
import com.consultorio.controlador.configuracion.Perfil;
import com.consultorio.controlador.configuracion.PreferenciasSistema;
import com.consultorio.controlador.configuracionEstructura.AgregarConsultorio;
import com.consultorio.controlador.configuracionEstructura.AgregarEdificio;
import com.consultorio.controlador.configuracionEstructura.EditarConsultorio;
import com.consultorio.controlador.configuracionEstructura.EditarEdificio;
import com.consultorio.controlador.consultasMedicas.IniciarConsulta;
import com.consultorio.controlador.consultasMedicas.PrescripcionElectronica;
import com.consultorio.controlador.consultasMedicas.RegistrarSintomasDiagnosticoTratamiento;
import com.consultorio.controlador.inventario.ControlStock;
import com.consultorio.controlador.inventario.MedicamentoDisponibles;
import com.consultorio.controlador.inventario.RegistroInsumos;
import com.consultorio.controlador.pacientes.BuscarEditarPaciente;
import com.consultorio.controlador.pacientes.HistorialClinico;
import com.consultorio.controlador.pacientes.RegistrarNuevoPaciente;
import com.consultorio.controlador.pagoYfacturacion.Facturacion;
import com.consultorio.controlador.pagoYfacturacion.HistorialPagosPaciente;
import com.consultorio.controlador.pagoYfacturacion.RegistrarPagos;
import com.consultorio.controlador.reportes.ExportarDatos;
import com.consultorio.controlador.reportes.ReportesAtencionMedica;
import com.consultorio.controlador.reportes.ReportesFinancieros;
import com.consultorio.controlador.usuarioPersonal.AgregarPersonal;
import com.consultorio.controlador.usuarioPersonal.MedicosAsistentes;
import com.consultorio.controlador.usuarioPersonal.PersonalGeneral;
import com.consultorio.controlador.usuarioPersonal.RolesPermisos;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.AllDesign;
import com.consultorio.util.alertas.Alerta;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
//import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.Objects;

import javafx.scene.control.Button;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;


public class PanelControl {



    //archivos
    String dirImage="";



   // Screen screen = Screen.getPrimary();
   // Rectangle2D dimensionPantalla = screen.getVisualBounds();

    //VARIABLES GLOBALES
    Rectangle2D dimensionPantalla= Screen.getPrimary().getBounds();
    Connection connection;


    //Crear una confirmacion Alerta

    Alerta alerta = new Alerta();

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
    private Button bEstructura;
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
    AllDesign design;
    public Usuario usuario;
    //agregar usuario
    public void setUsuario(Usuario usuario){ this.usuario=usuario; }

    public PanelControl(){
    this.categoria=categoria;
    //aqui sera llamado desde la dataBase
        design = new AllDesign(categoria);
    }

    //ENVIAR PARAMETROS GLOBALES AL PANEL CONTROL

    public void setConector(Connection connection){
  this.connection=connection;
        System.out.println("Conector en "+ this);
    }



//INICIAR EL FXML PANEL CONTROL
    @FXML
    public void initialize() {

        cargarRecursos();
        ajustesDinamicos();
        ajustesDiseno();


        Platform.runLater(()->{
            cargarControladores();
            loadImage();//CARGAR IMAGEN DE FONDO
        });


        // Aqui agregare un diseño rapido para identificar limites de objetos, no sera lo ultimo

      vbMenuIzquierdo.setStyle("-fx-background-color: #000b4b;");
      apPanelDinamico.setStyle("-fx-background-color: #ffffff; -fx-border-color: #101c8a;");
       vbSubMenu.setStyle("-fx-background-color: rgba(16,28,138,0.5);");

       //limpiar AnchorPanel Dinamico Y cargar FXML







    }

    //CARGAR RECURSOS
    public void cargarRecursos(){



        vbSubMenu.setVisible(false);
        //direccion de inicio por defecto
        String rutaDefectoInicio="/com/consultorio/vista/inicio.fxml";
        updateContenidoAnchorPane(rutaDefectoInicio, Inicio.class);

        //agregar diseño a los botones

        design.getDesignButton(bInicio,0);
        design.getDesignButton(bPacientes,0);
        design.getDesignButton(bCitas,0);
        design.getDesignButton(bReportes,0);
        design.getDesignButton(bUsuarioPersonal,0);
        design.getDesignButton(bEstructura,0);
        design.getDesignButton(bConsultasMedicas,0);
        design.getDesignButton(bInventario,0);
        design.getDesignButton(bPagoFacturacion,0);
        design.getDesignButton(bConfiguracion,0);
        design.getDesignButton(bCerraSesion,0);



    }
//LLAMADO DE DATABASE
        //CARGA DE CONTROLOADORES DESDE SET

    public void cargarControladores(){
        loadLabelUserName();
    }
    public void loadLabelUserName(){
        lbNameUser.setText(usuario.getUsuario());
    }

    //LLAMADO IMAGEN
    public void loadImage(){
        //llamar metodo DataBase
        dirImage=getDataBase(dirImage);

        if (Objects.equals(dirImage, "")) {dirImage="/resource/img/user_unknown.jpg";
            ivImageMenu.setImage(new Image(getClass().getResource(dirImage).toExternalForm()));
        }
        else {

            ivImageMenu.setImage(agregarFondoBlanco(new Image("file:"+dirImage)));

        }
        hacerImagenCircular();



    }
    public void hacerImagenCircular() {
        double radio = ivImageMenu.getFitWidth() / 2; // 📌 Toma la mitad del ancho como radio
        Circle clip = new Circle(radio, radio, radio); // 📌 Crea un círculo centrado
        ivImageMenu.setClip(clip); // 📌 Aplica el clip para recortar la imagen en círculo
    }


//LLAMADO A FXML

    public void cargarFXML(String cadena){
        //cargar el fxml
        //usare un switch para distribuir
        switch (cadena) {
            case "Inicio/DashBoard":
                System.out.println("Acción: Ir a inicio");
                updateContenidoAnchorPane("/com/consultorio/vista/inicio.fxml",Inicio.class);
                break;
            // Pacientes
            case "Registrar Nuevo Paciente":
                System.out.println("Acción: Registrar nuevo paciente");
                updateContenidoAnchorPane("/com/consultorio/vista/pacientes/registrar_nuevo_paciente.fxml", RegistrarNuevoPaciente.class);
                break;
            case "Buscar / editar pacientes":
                System.out.println("Acción: Buscar / editar pacientes");
                updateContenidoAnchorPane("/com/consultorio/vista/pacientes/buscar_editar_paciente.fxml", BuscarEditarPaciente.class);
                break;
            case "Historial clínico":
                System.out.println("Acción: Ver historial clínico");
                updateContenidoAnchorPane("/com/consultorio/vista/pacientes/historial_clinico.fxml", HistorialClinico.class);
                break;
            case "Adjuntar archivos (estudios, recetas, etc.)":
                System.out.println("Acción: Adjuntar archivos");
                break;

            // Citas
            case "Agendar nueva cita":
                System.out.println("Acción: Agendar nueva cita");
                updateContenidoAnchorPane("/com/consultorio/vista/citas/agendar_nueva_cita.fxml",AgendarNuevaCita.class);

                break;
            case "Ver calendario / agenda del día":
                System.out.println("Acción: Ver calendario / agenda del día");
                updateContenidoAnchorPane("/com/consultorio/vista/citas/agenda_dia.fxml",AgendaDia.class);
                break;
            case "Citas pendientes":
                System.out.println("Acción: Ver citas pendientes");
                updateContenidoAnchorPane("/com/consultorio/vista/citas/citas_pendientes.fxml",CitasPendientes.class);
                break;
            case "Citas pasadas":
                System.out.println("Acción: Ver citas pasadas");
                updateContenidoAnchorPane("/com/consultorio/vista/citas/citas_pasadas.fxml",CitasPasadas.class);
                break;

            // Consultas Médicas
            case "Iniciar consulta":
                System.out.println("Acción: Iniciar consulta médica");
                updateContenidoAnchorPane("/com/consultorio/vista/consultasMedicas/iniciar_consulta.fxml",IniciarConsulta.class);

                break;
            case "Registrar síntomas, diagnóstico, tratamiento":

                System.out.println("Acción: Registrar detalles de consulta médica");
                updateContenidoAnchorPane("/com/consultorio/vista/consultasMedicas/registrar_diagnostico_tratamiento_sintomas.fxml",RegistrarSintomasDiagnosticoTratamiento.class);

                break;
            case "Prescripción electrónica":
                System.out.println("Acción: Emitir prescripción electrónica");
                updateContenidoAnchorPane("/com/consultorio/vista/consultasMedicas/prescripcion_electronica.fxml",PrescripcionElectronica.class);

                break;

            // Inventario / Farmacia
            case "Medicamentos disponibles":
                System.out.println("Acción: Consultar medicamentos disponibles");
                updateContenidoAnchorPane("/com/consultorio/vista/inventario/medicamentos_disponibles.fxml",MedicamentoDisponibles.class);


                break;
            case "Registro de insumos":
                System.out.println("Acción: Registrar insumos");
                updateContenidoAnchorPane("/com/consultorio/vista/inventario/registro_insumos.fxml",RegistroInsumos.class);

                break;
            case "Control de stock":
                System.out.println("Acción: Control de stock");
                updateContenidoAnchorPane("/com/consultorio/vista/inventario/control_stock.fxml",ControlStock.class);

                break;

            // Pagos y Facturación
            case "Registrar pagos":
                System.out.println("Acción: Registrar pagos de pacientes");
                updateContenidoAnchorPane("/com/consultorio/vista/pagoYfacturacion/registrar_pagos.fxml",RegistrarPagos.class);
                break;
            case "Historial de pagos por paciente":
                System.out.println("Acción: Ver historial de pagos por paciente");
                updateContenidoAnchorPane("/com/consultorio/vista/pagoYfacturacion/historial_pagos_pacientes.fxml", HistorialPagosPaciente.class);
                break;
            case "Facturación":
                System.out.println("Acción: Realizar facturación");
                updateContenidoAnchorPane("/com/consultorio/vista/pagoYfacturacion/facturacion.fxml",Facturacion.class);
                break;

            // Reportes
            case "Reportes de atención médica":
                System.out.println("Acción: Generar reportes de atención médica");
                updateContenidoAnchorPane("/com/consultorio/vista/reportes/reportes_atencion_medica.fxml",ReportesAtencionMedica.class);
                break;
            case "Reportes financieros":
                System.out.println("Acción: Generar reportes financieros");
                updateContenidoAnchorPane("/com/consultorio/vista/reportes/reportes_financieros.fxml",ReportesFinancieros.class);
                break;
            case "Exportar datos":
                System.out.println("Acción: Exportar datos del sistema");
                updateContenidoAnchorPane("/com/consultorio/vista/reportes/exportar_datos.fxml", ExportarDatos.class);
                break;

             //Configuracion Estructura
            case "Agregar edificio":
                System.out.println("Accion: Agregar edificio");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracionEstructura/agregar_edificio.fxml", AgregarEdificio.class);
                break;
            case "Editar edificio":
                System.out.println("Accion: Editar edificio");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracionEstructura/editar_edificio.fxml", EditarEdificio.class);
                break;
            case "Agregar consultorio":
                System.out.println("Accion: Agregar consultorio");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracionEstructura/agregar_consultorio.fxml", AgregarConsultorio.class);
               break;
            case "Editar consultorio":
                System.out.println("Accion: Editar consultorio");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracionEstructura/editar_consultorio.fxml", EditarConsultorio.class);
                break;

            // Usuarios / Personal
            case "Agregar personal":
                System.out.println("Acción: Agregar personal");
                updateContenidoAnchorPane("/com/consultorio/vista/usuarioPersonal/agregar_personal.fxml", AgregarPersonal.class);

                break;

            case "Personal general":
                System.out.println("Acción: Personal general");
                updateContenidoAnchorPane("/com/consultorio/vista/usuarioPersonal/personal_general.fxml",PersonalGeneral.class);
                break;
            case "Médicos y asistentes":
                System.out.println("Acción: Administrar médicos y asistentes");
                updateContenidoAnchorPane("/com/consultorio/vista/usuarioPersonal/medicos_asistentes.fxml",MedicosAsistentes.class);
                break;
            case "Roles y permisos":
                System.out.println("Acción: Configurar roles y permisos");
                updateContenidoAnchorPane("/com/consultorio/vista/usuarioPersonal/roles_permisos.fxml", RolesPermisos.class);
                break;

            // Configuración
            case "Datos del consultorio":
                System.out.println("Acción: Configurar datos del consultorio");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracion/datos_consultorio.fxml",DatosConsultorio.class);
                break;
            case "Preferencias del sistema":
                System.out.println("Acción: Configurar preferencias del sistema");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracion/preferencias_sistema.fxml", PreferenciasSistema.class);
                break;
            case "Notificaciones / alertas":
                System.out.println("Acción: Configurar notificaciones y alertas");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracion/notificaciones_alerta.fxml", NotificacionAlerta.class);
                break;
            case "Perfil":
                System.out.println("Acción: Configurar notificaciones y alertas");
                updateContenidoAnchorPane("/com/consultorio/vista/configuracion/perfil.fxml", Perfil.class);
                break;

            // Cerrar sesión / Salir
            case "Salir del sistema":
                System.out.println("Acción:salir");
                if (alerta.mostrarConfirmacion("¿Deseas Salir del sistema?")) {
                    SalirSistema salirSistema= new SalirSistema();
                    salirSistema.setConector(connection);
                    salirSistema.closeConection();
                    Platform.exit();
                }

                break;
            case "Cerrar sesion":
                System.out.println("Acción: Cerrar sesión");
                if (alerta.mostrarConfirmacion("¿Deseas Cerrar Sesion?")) {
                    CerrarSesion cerrarSesion= new CerrarSesion();
                    cerrarSesion.setConector(connection);
                    cerrarSesion.closeConection();
                    Platform.exit();
                }

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
        design.getDesignButton(bEstructura,0);
        design.getDesignButton(bConsultasMedicas,0);
        design.getDesignButton(bInventario,0);
        design.getDesignButton(bPagoFacturacion,0);
        design.getDesignButton(bConfiguracion,0);
        design.getDesignButton(bCerraSesion,0);
    }

    public void mostrarSubmenu(){
        vbSubMenu.setVisible(true);
    }


    public void asignarControladores(){//para enviar parametros


    }
    //aqui seran llamado las conexiones
    public <T> void getControladorConection(T controlador, Class <T> tipoControlador){
        if(tipoControlador.isInstance(controlador)){
            if(controlador instanceof Inicio){
                ((Inicio) controlador).setConector(connection);
            }
            if(controlador instanceof RegistrarNuevoPaciente){
                ((RegistrarNuevoPaciente) controlador).setConector(connection);
                ((RegistrarNuevoPaciente) controlador).setUsuario(usuario);

            }
            if(controlador instanceof BuscarEditarPaciente){
                ((BuscarEditarPaciente) controlador).setConector(connection);
                ((BuscarEditarPaciente) controlador).setUsuario(usuario);
            }
            if(controlador instanceof HistorialClinico){
                ((HistorialClinico) controlador).setConector(connection);
            }
            if(controlador instanceof AgendarNuevaCita){
                ((AgendarNuevaCita) controlador).setConector(connection);
                ((AgendarNuevaCita) controlador).setUsuario(usuario);
            }
            if(controlador instanceof AgendaDia){
                ((AgendaDia) controlador).setConector(connection);
            }
            if(controlador instanceof CitasPendientes){
                ((CitasPendientes) controlador).setConector(connection);
            }
            if(controlador instanceof CitasPasadas){
                ((CitasPasadas) controlador).setConector(connection);
            }
            if(controlador instanceof IniciarConsulta){
                ((IniciarConsulta) controlador).setConector(connection);
            }
            if(controlador instanceof RegistrarSintomasDiagnosticoTratamiento){
                ((RegistrarSintomasDiagnosticoTratamiento) controlador).setConector(connection);
            }
            if(controlador instanceof PrescripcionElectronica){
                ((PrescripcionElectronica) controlador).setConector(connection);
            }
            if(controlador instanceof MedicamentoDisponibles){
                ((MedicamentoDisponibles) controlador).setConector(connection);
            }
            if(controlador instanceof RegistroInsumos){
                ((RegistroInsumos) controlador).setConector(connection);
            }
            if(controlador instanceof ControlStock){
                ((ControlStock) controlador).setConector(connection);
            }
            if(controlador instanceof RegistrarPagos){
                ((RegistrarPagos) controlador).setConector(connection);
            }
            if(controlador instanceof HistorialClinico){
                ((HistorialClinico) controlador).setConector(connection);
            }
            if(controlador instanceof Facturacion){
                ((Facturacion) controlador).setConector(connection);
            }
            if(controlador instanceof ReportesAtencionMedica){
                ((ReportesAtencionMedica) controlador).setConector(connection);
            }
            if(controlador instanceof ReportesFinancieros){
                ((ReportesFinancieros) controlador).setConector(connection);
            }
            if(controlador instanceof ExportarDatos){
                ((ExportarDatos) controlador).setConector(connection);
            }
            if(controlador instanceof AgregarEdificio){
                ((AgregarEdificio) controlador).setConector(connection);
                ((AgregarEdificio) controlador).setUsuario(usuario);
            }

            if(controlador instanceof EditarEdificio){
                ((EditarEdificio) controlador).setConector(connection);
                ((EditarEdificio) controlador).setUsuario(usuario);
            }
            if(controlador instanceof AgregarConsultorio){
                ((AgregarConsultorio) controlador).setConector(connection);
                ((AgregarConsultorio) controlador).setUsuario(usuario);
            }

            if(controlador instanceof EditarConsultorio){
                ((EditarConsultorio) controlador).setConector(connection);
                ((EditarConsultorio) controlador).setUsuario(usuario);
            }

            if(controlador instanceof AgregarPersonal){
                ((AgregarPersonal) controlador).setConector(connection);
                ((AgregarPersonal) controlador).setUsuario(usuario);
            }
            if(controlador instanceof PersonalGeneral){
                ((PersonalGeneral) controlador).setConector(connection);
                ((PersonalGeneral) controlador).setUsuario(usuario);
            }
            if(controlador instanceof MedicosAsistentes){
                ((MedicosAsistentes) controlador).setConector(connection);
                ((MedicosAsistentes) controlador).setUsuario(usuario);
            }
            if(controlador instanceof RolesPermisos){
                ((RolesPermisos) controlador).setConector(connection);
                ((RolesPermisos) controlador).setUsuario(usuario);
            }
            if(controlador instanceof Perfil){
                ((Perfil) controlador).setConector(connection);
            }
            if(controlador instanceof DatosConsultorio){
                ((DatosConsultorio) controlador).setConector(connection);
            }
            if(controlador instanceof PreferenciasSistema){
                ((PreferenciasSistema) controlador).setConector(connection);
            }
            if(controlador instanceof NotificacionAlerta){
                ((NotificacionAlerta) controlador).setConector(connection);
            }




        }
    }

    public <T> void updateContenidoAnchorPane(String ruta, Class<T> tipoControlador){
        try {

            apPanelDinamico.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            contenidoAnchorPanelDinamico= loader.load();
            T controlador =loader.getController();
           getControladorConection(controlador,tipoControlador);


            apPanelDinamico.getChildren().add(contenidoAnchorPanelDinamico);





            // Ajustar para que se adapte al tamaño del AnchorPane
            AnchorPane.setTopAnchor(contenidoAnchorPanelDinamico, 0.0);
            AnchorPane.setBottomAnchor(contenidoAnchorPanelDinamico, 0.0);
            AnchorPane.setLeftAnchor(contenidoAnchorPanelDinamico, 0.0);
           AnchorPane.setRightAnchor(contenidoAnchorPanelDinamico, 0.0);


            //cargar los datos de la base de datos

        }catch (Exception e){
            System.out.println(e.getMessage());
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
        cadenaSubMenu = new String[]{"Agregar personal","Personal general","Médicos y asistentes", "Roles y permisos"};
        vbSubMenu.getChildren().clear();
        imprimirSubMenu(cadenaSubMenu);
    }

    @FXML
    public void actionConfiguracicoEstructura(){
        design.getDesignButton(bEstructura,1);
        cadenaSubMenu= new String[]{"Agregar edificio","Editar edificio","Agregar consultorio","Editar consultorio" };
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
        cadenaSubMenu = new String[]{"Salir del sistema","Cerrar sesion"};
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

        String foto= usuario.getEmpleado().getFoto();
        dirImage=foto;
        if (dirImage==null)return "";

        return  foto;
    }

    private Image agregarFondoBlanco(Image original) {
        WritableImage nuevaImagen = new WritableImage((int) original.getWidth(), (int) original.getHeight());
        PixelWriter pixelWriter = nuevaImagen.getPixelWriter();
        PixelReader pixelReader = original.getPixelReader();

        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                Color colorPixel = pixelReader.getColor(x, y);
                pixelWriter.setColor(x, y, colorPixel.getOpacity() == 0 ? Color.WHITE : colorPixel);
            }
        }
        return nuevaImagen;
    }

    public void setDataBase(String dirImage){}

}
