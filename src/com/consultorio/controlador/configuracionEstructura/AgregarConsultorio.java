package com.consultorio.controlador.configuracionEstructura;

import com.consultorio.modelo.estructura.Consultorio;
import com.consultorio.modelo.estructura.Edificio;
import com.consultorio.modelo.estructura.RegistroConsultorio;
import com.consultorio.modelo.estructura.RegistroEdificio;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.GetFecha;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;
import com.consultorio.util.conection.modeloDataBase.estructura.ConsultorioDB;
import com.consultorio.util.conection.modeloDataBase.estructura.EdificioDB;
import com.consultorio.util.conection.modeloDataBase.estructura.RegistroConsultorioDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.time.DateTimeException;
import java.util.List;

public class AgregarConsultorio {


    //agregar un objeto de error
    VentanaErrores ventanaErrores = new VentanaErrores();
    String mensajeError="";

    AlertaAprobacion alertaAprobacion= new AlertaAprobacion();



    public  EdificioDB edificioDB= new EdificioDB();
   ConsultorioDB consultorioDB = new ConsultorioDB();
    RegistroConsultorioDB registroConsultorioDB = new RegistroConsultorioDB();




    ///ID DE DATOS DE Edificio

    @FXML
    TextField tfNumConsultorio;
    @FXML
    ComboBox cbEspecialidad;
    @FXML
    ComboBox cbIdEdificio;
    @FXML
    TextField tfNumPiso;






    //AQUI SE AGREGARAN LOS ITEMS

    @FXML
    Button btnRegistrar;


    @FXML
    GridPane gridPane;

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

    public void cargarBDEdificio(){
        consultorioDB.setConnection(connection);

    }



    @FXML
    public void initialize() {

        cargar();

        Platform.runLater(()->{
            //cargar todo lo demas
            edificioDB.setConnection(connection);
            consultorioDB.setConnection(connection);
            registroConsultorioDB.setConnection(connection);

        });

    }

    //cargar funciones action

    @FXML
    public void actionRegistrar(){



        System.out.println("Entrar dentro de la funcion ejecutar");


        try {

            int verificarEnteros=Integer.parseInt(tfNumConsultorio.getText());
            verificarEnteros=Integer.parseInt(tfNumPiso.getText());

            Consultorio consultorio=new Consultorio();
            consultorioDB.setConsultorio(cargarConsultorio(consultorio));
            registroConsultorioDB.setRegistroConsultorio( cargarRegistroConsultorio(new RegistroConsultorio()));


            tfNumConsultorio.clear();
            cbEspecialidad.getItems().clear();
            cbIdEdificio.getItems().clear();
            tfNumPiso.clear();







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

    public Consultorio cargarConsultorio(Consultorio consultorio){
        String numConsultorio = tfNumConsultorio.getText();
        String especialidad= cbEspecialidad.getValue().toString();
        Edificio edificio= edificioDB.getEdificio(Integer.parseInt((String) cbIdEdificio.getValue()));
        String numPiso= tfNumPiso.getText();


        System.out.println(numConsultorio);
        System.out.println(especialidad);
        System.out.println(edificio);
        System.out.println(numPiso);


        consultorio.setnConsultorio(numConsultorio);
        consultorio.setEspecialidad(especialidad);
        consultorio.setEdificio(edificio);
        consultorio.setNumeroPiso(numPiso);



        return consultorio;
    }

    public RegistroConsultorio cargarRegistroConsultorio(RegistroConsultorio registroConsultorio){

        registroConsultorio.setIdUsuario(usuario.getId());
        registroConsultorio.setNConsultorio(consultorioDB.obtenerUltimoIdConsultorio());
        registroConsultorio.setFechaRegistro(new GetFecha().getFechaDateActual());
        return registroConsultorio;
    }

    //___________________________________________________________________


    //metodos de ejecucion

    public void cargar(){
        //cargarImgDefault();
        cargarItemsComboBoxIDEdificio();

        // cargarItemsEdad();
    }
    public void cargarItemsComboBoxIDEdificio(){

        List<Edificio> lista = edificioDB.getEdificios();
        List<String> listaId = edificioDB.getIdEdificios();

        cbIdEdificio.setItems(FXCollections.observableArrayList(listaId));
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
