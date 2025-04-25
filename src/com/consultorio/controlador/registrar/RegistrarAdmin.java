package com.consultorio.controlador.registrar;

import com.consultorio.modelo.personal.Empleado;
import com.consultorio.util.errores.VentanaErrores;
import com.consultorio.util.validadCorreo.ValidadorRegexCorreo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.consultorio.util.validadCorreo.ValidadorRegexCorreo.validarCorreo;


public class RegistrarAdmin {
    VentanaErrores ventanaErrores = new VentanaErrores();
    ValidadorRegexCorreo validarCorreo = new ValidadorRegexCorreo();


    @FXML
    ChoiceBox cbSexo;
    @FXML
    ChoiceBox cbEdad;

    @FXML
    Button btnIngresar;

    //AGREGAR LO TEXTFIELD Y DEMAS DATOS DEL EMPLEADO
@FXML
    TextField lbNombre;
@FXML
TextField lbApaterno;
@FXML
TextField lbAmaterno;
@FXML
Label lbOcupacion;
@FXML
Label lbEspecialidad;

@FXML
TextField lbDireccion;
@FXML
TextField lbTelefono;
@FXML
TextField lbEmail;
@FXML
    DatePicker dpFechaNacimiento;

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");




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


       cbSexo.setValue("masculino");
    }

    public void cargarCbEdad(){
        ArrayList<String> lista=new ArrayList<>();
        for(int i=18;i<151;i++)
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
        String mensajeError="";
        try {
            if(
                  ! ( lbNombre.getText().equals("") || lbApaterno.getText().equals("") || lbAmaterno.getText().equals("") ||
                    lbDireccion.getText().equals("") || lbTelefono.getText().equals("") || lbEmail.getText().equals("") )
            ){
                if (validarCorreo(lbEmail.getText())) {

                    cargarRegistroUsuarioAdmin();
                }
                else ventanaErrores.ventanaErrorClasico("Correo invalido");

            }  else ventanaErrores.ventanaErrorClasico("Datos si rellenar");


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



    }



    public void cargarRegistroUsuarioAdmin() throws IOException {
       try {
           FXMLLoader loader = new FXMLLoader(RegistrarCentroMedico.class.getResource("/com/consultorio/vista/registrar/registrar_usuario_admin.fxml"));
           Parent root = loader.load();
           stage.setScene(new Scene(root));
           RegistrarUsuarioAdmin controlador= loader.getController();

           controlador.setConector(connection);
           controlador.setStage(stage);
           Empleado empleado= actionRegistrarEmpleado(new Empleado());
           controlador.setEmpleado(empleado);



            stage.centerOnScreen();

           stage.initStyle(StageStyle.UNDECORATED);
           //primaryStage.sizeToScene();
           stage.setTitle("Registro Usuario Propietario");
           stage.setMaximized(true);

           stage.show();


       }catch (Exception e){
           System.out.println(e.getMessage());
       }
    }
    public Empleado actionRegistrarEmpleado(Empleado empleado) {
            empleado.setId("1");
            empleado.setCurp("");
            empleado.setNombre(lbNombre.getText());
            empleado.setaPaterno(lbApaterno.getText());
            empleado.setaMaterno(lbAmaterno.getText());
            empleado.setFnacimiento(Date.valueOf(dpFechaNacimiento.getValue()));
            empleado.setDireccion(lbDireccion.getText());
            empleado.setTelefono(lbTelefono.getText());
            empleado.setEmail(lbEmail.getText());
            empleado.setFoto("");
            empleado.setOcupacion(lbOcupacion.getText());
            empleado.setEspecialidad(lbEspecialidad.getText());
        LocalDate fechaActual = LocalDate.now(); // Obtener la fecha actual
        java.util.Date date = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Convertir a Date
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActualString=formato.format(date);
        //empleado.setFecha_ingreso((date));
        try{
            empleado.setFecha_ingreso(formato.parse(fechaActualString));
            System.out.println(fechaActualString);
            System.out.println(empleado.getFecha_ingreso());


            System.out.println("Fecha agregada con exito");
        }catch (Exception e){
            System.out.println("Falla al asignar fechaActual");
        }
            empleado.setEdad((String) cbEdad.getValue());
            empleado.setSexo((String) cbSexo.getValue());
        System.out.println("Empleado registrado");

        System.out.println(empleado);


        return empleado;
    }




}
