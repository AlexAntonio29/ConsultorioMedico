package com.consultorio.controlador.configuracionEstructura;






import com.consultorio.controlador.usuarioPersonal.AgregarPersonal;
import com.consultorio.modelo.estructura.Edificio;
import com.consultorio.modelo.estructura.RegistroEdificio;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.CargarFXML;
import com.consultorio.util.GetFecha;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;
import com.consultorio.util.conection.modeloDataBase.estructura.EdificioDB;
import com.consultorio.util.conection.modeloDataBase.estructura.RegistroEdificioDB;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.time.DateTimeException;


public class AgregarEdificio {

    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }


    public Usuario usuario;

    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }

    public AgregarEdificio(){}

    @FXML
    AnchorPane rootPanel;
    String ruta="/com/consultorio/vista/configuracionEstructura/agregar_edificio.fxml";
    CargarFXML cargarFXML= new CargarFXML();


    //agregar un objeto de error
    VentanaErrores ventanaErrores = new VentanaErrores();
    String mensajeError="";

    AlertaAprobacion alertaAprobacion= new AlertaAprobacion();




    EdificioDB edificioDB = new EdificioDB();
    RegistroEdificioDB registroEdificioDB = new RegistroEdificioDB();




    ///ID DE DATOS DE Edificio

    @FXML
    TextField tfNumEdificio;
    @FXML
    TextField tfNombreEdificio;
    @FXML
    TextField tfDireccion;
    @FXML
    TextField tfNumPisos;





    String dirImage="";
    //AQUI SE AGREGARAN LOS ITEMS

    @FXML
    Button btnRegistrar;


    @FXML
    GridPane gridPane;



    public void cargarBDEdificio(){
        edificioDB.setConnection(connection);

    }



    @FXML
    public void initialize() {

        cargar();

        Platform.runLater(()->{
            //cargar todo lo demas
            edificioDB.setConnection(connection);
            registroEdificioDB.setConnection(connection);

            cargarFXML.setConector(connection);
            cargarFXML.setUsuario(usuario);

        });

    }

    //cargar funciones action

    @FXML
    public void actionRegistrar(){



        System.out.println("Entrar dentro de la funcion ejecutar");


        try {

            int verificarEnteros=Integer.parseInt(tfNumEdificio.getText());
            verificarEnteros=Integer.parseInt(tfNumPisos.getText());

            Edificio edificio=new Edificio();
            edificioDB.setEdificio(cargarEdificio(edificio));
            registroEdificioDB.setRegistroEdificio( cargarRegistroEdificio(new RegistroEdificio(),edificio));

/*
            tfNumEdificio.clear();
            tfNombreEdificio.clear();
            tfDireccion.clear();
            tfNumPisos.clear();
*/

            cargarFXML.updateContenidoAnchorPane(ruta, AgregarEdificio.class,rootPanel);







        }catch (NumberFormatException e){
            mensajeError="Error de Formato favor de corregirlo";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }catch (DateTimeException e){
            mensajeError="Error al capturar fecha";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }catch (NullPointerException e){
            mensajeError="Datos no capturados";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }
        catch (Exception e){
            mensajeError="Error desconocido";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }

        System.out.println();
    }

    public Edificio cargarEdificio(Edificio edificio){
        String numEdificio = tfNumEdificio.getText();
        String nombreEdificio= tfNombreEdificio.getText();
        String direccion=tfDireccion.getText();
        String numPisos=tfNumPisos.getText();


        System.out.println(numEdificio);
        System.out.println(nombreEdificio);
        System.out.println(direccion);
        System.out.println(numPisos);


        edificio.setNumeroEdificio(numEdificio);
        edificio.setNombreEdificio(nombreEdificio);
        edificio.setDireccionEdificio(direccion);
        edificio.setNumeroPisos(numPisos);



        return edificio;
    }

    public RegistroEdificio cargarRegistroEdificio(RegistroEdificio registroEdificio, Edificio edificio){

        registroEdificio.setIdUsuario(Integer.parseInt(usuario.getId()));
        registroEdificio.setIdEdificio(Integer.parseInt(edificioDB.obtenerUltimoIdEdificio()));

        registroEdificio.setFechaRegistro(new GetFecha().getFechaDateActual());

        return registroEdificio;
    }

    //___________________________________________________________________


    //metodos de ejecucion

    public void cargar(){
        //cargarImgDefault();


        // cargarItemsEdad();
    }



   /* public void cargarItemsEdad(){
        comboTipoEdad.getItems().clear();

        for (int i=0;i<151;i++){
            listaEdad.add(String.valueOf(i));
        }
        comboTipoEdad.getItems().addAll(listaEdad);
        comboTipoEdad.setValue(listaEdad.get(0));
    }*/

    public String getDataBase(String typeData){

        //llamar a la Base de datos para proporcionar direccion
        String getData="";

        return  getData;
    }

    public void setDataBase(String dirImage){}


}

