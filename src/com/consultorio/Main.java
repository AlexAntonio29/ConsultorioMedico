package com.consultorio;

import com.consultorio.controlador.PanelControl;
import com.consultorio.controlador.iniciarSesion.IniciarSesion;
import com.consultorio.controlador.registrar.RegistrarCentroMedico;
import com.consultorio.controlador.registrar.RegistrarAdmin;
import com.consultorio.util.conection.ConectionDB;
import com.consultorio.util.conection.actions.DBAccionesBasicas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;


public class Main extends Application  {
    //variables globales
    Stage primaryStage;
    ConectionDB conectionDB = new ConectionDB();
    Connection conexionDB = ConectionDB.getConn();//obtener la conexion para la BD
    Rectangle2D dimensionPantalla= Screen.getPrimary().getBounds();
        //variable global DB
    DBAccionesBasicas accionesBasicasDB = new DBAccionesBasicas();


    /*
    public void cargarPanel(Stage primaryStage) throws IOException {



        //Nos conectamos a un archivo llamado FXML llamado panel_control.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/panel_control.fxml"));
        Parent root = loader.load();//cargamos

        PanelControl panelControl = loader.getController();
        panelControl.setConector(conexionDB);


        //hacer que no tenga el boton de cerra, minimizar ni maximizar
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.sizeToScene();
        primaryStage.setTitle("Panel Control");//definir titulo
        //primaryStage.setAlwaysOnTop(true);//mantenerse encima de todos

        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root));//creacion de escena
        primaryStage.setWidth(dimensionPantalla.getWidth());
        primaryStage.setHeight(dimensionPantalla.getHeight());
        primaryStage.setMinWidth(dimensionPantalla.getWidth());
        primaryStage.setMinHeight(dimensionPantalla.getHeight());
        primaryStage.setMaxWidth(dimensionPantalla.getWidth());
        primaryStage.setMaxHeight(dimensionPantalla.getHeight());


        primaryStage.show();//mostrar
    }
*/
    @Override
    public void start(Stage primaryStage)


            throws Exception {
        accionesBasicasDB.setConector(conexionDB);//

      if(accionesBasicasDB.verificarTablaVacia("centro_medico")&&
              accionesBasicasDB.verificarTablaVacia("usuario"))
      {
          System.out.println("estan vacias");
          registroPropietario(primaryStage);
          //iniciarSesion(primaryStage);
      }
      else {
          System.out.println("no estan vacias");

      }

        //revisar aqui si ya existe el empleado 0, o la tabla admin no esta vacio
        //si no existe mandar a registro





       //si existe ir a iniciar sesion

    }

    public void iniciarSesion(Stage primaryStage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/iniciarSesion/iniciar_sesion.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));//creacion de escena
        IniciarSesion controlador= loader.getController();

        controlador.setConector(conexionDB);
        controlador.setStage(primaryStage);

        primaryStage.centerOnScreen();

        primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.sizeToScene();
        primaryStage.setTitle("Iniciar Sesion");
        // primaryStage.setMaximized(true);

        //primaryStage.setWidth(400);
        //primaryStage.setHeight(600);
        //primaryStage.setMinWidth(400);
        // primaryStage.setMinHeight(600);
        //primaryStage.setMaxWidth(400);
        // primaryStage.setMaxHeight(600);

        primaryStage.show();


        //mostrar

        //cargarPanel(primaryStage);


        //obtener la dimension de la pantalla cualquiera
        //Screen screen = Screen.getPrimary();
        // Rectangle2D dimensionPantalla = screen.getVisualBounds();
    }

   public void registroPropietario(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/registrar/registrar_admin.fxml"));
        Parent root = loader.load();
       primaryStage.setScene(new Scene(root));//creacion de escena
        RegistrarAdmin controlador= loader.getController();

        controlador.setConector(conexionDB);
        controlador.setStage(primaryStage);

       primaryStage.centerOnScreen();

        primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.sizeToScene();
        primaryStage.setTitle("Registro Administrador");
       // primaryStage.setMaximized(true);

        //primaryStage.setWidth(400);
        //primaryStage.setHeight(600);
        //primaryStage.setMinWidth(400);
       // primaryStage.setMinHeight(600);
        //primaryStage.setMaxWidth(400);
       // primaryStage.setMaxHeight(600);

        primaryStage.show();


        //mostrar

        //cargarPanel(primaryStage);


        //obtener la dimension de la pantalla cualquiera
        //Screen screen = Screen.getPrimary();
        // Rectangle2D dimensionPantalla = screen.getVisualBounds();

    }

    public void registroCentroMedico(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/registrar/registrar_centro_medico.fxml"));
        Parent root = loader.load();
        RegistrarCentroMedico controlador= loader.getController();
        controlador.setConector(conexionDB);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.sizeToScene();
        primaryStage.setTitle("Registro Centro Medico");
        //primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root));//creacion de escena
        //primaryStage.setWidth(400);
        //primaryStage.setHeight(600);
        //primaryStage.setMinWidth(400);
        // primaryStage.setMinHeight(600);
        //primaryStage.setMaxWidth(400);
        // primaryStage.setMaxHeight(600);

        primaryStage.show();//mostrar

        //cargarPanel(primaryStage);


        //obtener la dimension de la pantalla cualquiera
        //Screen screen = Screen.getPrimary();
        // Rectangle2D dimensionPantalla = screen.getVisualBounds();

    }




    public static void main(String[] args) {
        launch(args);
        //hola
    }
}

