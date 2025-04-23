package com.consultorio.controlador.pacientes;

import com.consultorio.util.errores.VentanaErrores;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;


public class RegistrarNuevoPaciente {

    //agregar un objeto de error
    VentanaErrores ventanaErrores = new VentanaErrores();

    ArrayList <String> listaEdad = new ArrayList <> ();

    ///ID DE DATOS DE PACIENTE
    @FXML
    TextField tfNombre;
   @FXML
           TextField tfApellidoPaterno;
   @FXML
           TextField tfApellidoMaterno;
   @FXML
    DatePicker dpFechaNacimiento;
   @FXML
           TextField tfDireccion;
   @FXML
           TextField tfTelefono;
   @FXML
       ComboBox comboTipoSexo;
   @FXML
   ComboBox comboTipoEdad;
   @FXML
           TextField tfEmail;




    String dirImage="";
//AQUI SE AGREGARAN LOS ITEMS
    @FXML
    ImageView ivFoto;
    @FXML
   Button btnRegistrar;
    public Connection connection;

    //obtener conector
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
    }



    @FXML
    public void initialize() {

        cargar();

    }

    //cargar funciones action

    @FXML
    public void actionRegistrar(){

        String mensajeError="";

        System.out.println("Entrar dentro de la funcion ejecutar");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            String foto="";
            String nombre=tfNombre.getText();
            String apellido_paterno=tfApellidoPaterno.getText();
            String apellido_materno=tfApellidoMaterno.getText();
            String fecha=dpFechaNacimiento.getValue().toString();
            LocalDate.parse(fecha,formato);
            String direccion=tfDireccion.getText();
            String telefono=tfTelefono.getText();
            String sexo=comboTipoSexo.getValue().toString();
            int edad=Integer.parseInt(comboTipoEdad.getValue().toString());
            String email=tfEmail.getText();



            System.out.println(nombre);
            System.out.println(apellido_paterno);
            System.out.println(apellido_materno);
            System.out.println(fecha);
            System.out.println(direccion);
            System.out.println(telefono);
            System.out.println(sexo);
            System.out.println(edad);
            System.out.println(email);
            System.out.println();





        }catch (NumberFormatException e){
            mensajeError="Error de Formato favor de corregirlo";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }catch (DateTimeException e){
            mensajeError="Error al capturar fecha";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }catch (NullPointerException e){
            mensajeError="No se ha seleccionado el sexo";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }
        catch (Exception e){
            mensajeError="Error desconocido";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }

        System.out.println();
    }


    //___________________________________________________________________



    //metodos de ejecucion

    public void cargar(){
        cargarFoto();
        cargarItemsEdad();
    }

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

    public void cargarItemsEdad(){
        comboTipoEdad.getItems().clear();

        for (int i=0;i<151;i++){
            listaEdad.add(String.valueOf(i));
        }
        comboTipoEdad.getItems().addAll(listaEdad);
        comboTipoEdad.setValue(listaEdad.get(0));
    }

    public String getDataBase(String typeData){

        //llamar a la Base de datos para proporcionar direccion
        String getData="";

        return  getData;
    }

    public void setDataBase(String dirImage){}

}
