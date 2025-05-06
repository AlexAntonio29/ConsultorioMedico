package com.consultorio.controlador.usuarioPersonal;

import com.consultorio.modelo.personal.Empleado;
import com.consultorio.modelo.personal.RegistroUsuario;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.CargarFXML;
import com.consultorio.util.GetFecha;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;
import com.consultorio.util.conection.modeloDataBase.personal.EmpleadoDB;
import com.consultorio.util.conection.modeloDataBase.personal.RegistroUsuarioDB;
import com.consultorio.util.conection.modeloDataBase.personal.UsuarioDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.security.SecureRandom;

import java.sql.Connection;
import java.time.DateTimeException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RolesPermisos {

    public RolesPermisos() {}

    @FXML
    AnchorPane rootPanel;
    String ruta="/com/consultorio/vista/usuarioPersonal/roles_permisos.fxml";

    CargarFXML cargarFXML= new CargarFXML();


    //agregar un objeto de error
    VentanaErrores ventanaErrores = new VentanaErrores();
    String mensajeError="";


    AlertaAprobacion alertaAprobacion= new AlertaAprobacion();



    EmpleadoDB empleadoDB = new EmpleadoDB();
    UsuarioDB usuarioDB = new UsuarioDB();
    RegistroUsuarioDB registroUsuarioDB = new RegistroUsuarioDB();




    ///ID DE DATOS DE Edificio

    @FXML ComboBox comboEmpleado;
    @FXML
    TextField txtUsuario;
    @FXML
    TextField txtPassword;
    @FXML
    TextField txtPassword2;







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
        usuarioDB.setConector(connection);

    }



    @FXML
    public void initialize() {



        Platform.runLater(()->{
            //cargar todo lo demas
            empleadoDB.setConector(connection);
            usuarioDB.setConector(connection);
            registroUsuarioDB.setConnection(connection);

            cargarFXML.setConector(connection);
            cargarFXML.setConector(connection);

            cargar();

        });

    }

    //cargar funciones action

    @FXML
    public void actionRegistrar()throws IllegalStateException,IOException {



        System.out.println("Entrar dentro de la funcion ejecutar");


        try {

           if (comboEmpleado.getValue()==null||comboEmpleado.getValue().equals("")||txtUsuario.getText().isEmpty()
           ||txtPassword.getText().isEmpty()||txtPassword2.getText().isEmpty()
           ){
               throw new IOException("Datos No Capturados");
           }
           else {

                   if (Objects.equals(txtPassword.getText(), txtPassword2.getText())){
                       if (new AlertaAprobacion().mostrarConfirmacion("Deseas registrar al usuario?")) {

                           Usuario usuarioTemporal = new Usuario();
                       usuarioDB.setUsuario(cargarUsuario(usuarioTemporal));
                       registroUsuarioDB.setRegistroUsuario(cargarRegistroUsuario(new RegistroUsuario()));

                      /*comboEmpleado.setValue(null);
                       txtUsuario.clear();
                       txtPassword.clear();
                       txtPassword2.clear();*/
                           cargarFXML.updateContenidoAnchorPane(ruta, RolesPermisos.class,rootPanel);


                   }
                   }
                   else throw new IllegalStateException("Datos no Capturados");;



           }








        }catch (NumberFormatException e){
            mensajeError="Error de Formato favor de corregirlo";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }catch (DateTimeException e){
            mensajeError="Error al capturar fecha";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }catch (NullPointerException e){
            mensajeError="Datos no capturados";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }catch (IllegalStateException e){
            mensajeError="Password no coincide";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }catch (IOException e){
            mensajeError="Datos no capturados";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }

        catch (Exception e){


            mensajeError="Error desconocido";
            ventanaErrores.ventanaErrorClasico(mensajeError);
        }

        System.out.println();
    }

    public Usuario cargarUsuario(Usuario usuario){
        String usuarioText = txtUsuario.getText();
        String password= txtPassword.getText();
       Empleado empleado = empleadoDB.getEmpleado(comboEmpleado.getValue().toString());


        System.out.println(usuarioText);
        System.out.println(password);
        System.out.println(password);
        System.out.println(empleado);


        usuario.setUsuario(usuarioText);
        usuario.setPassword(password);
        usuario.setEmpleado(empleado);



        return usuario;
    }

    public RegistroUsuario cargarRegistroUsuario(RegistroUsuario registroUsuario){

        String usuarioResponsable=usuario.getId();
        String usuarioAgregado = usuarioDB.obtenerUltimoIdUsuario();


        System.out.println(usuarioResponsable);
        System.out.println(usuarioAgregado);


        registroUsuario.setUsuarioResponsable(usuarioResponsable);
        registroUsuario.setUsuarioAgregado(usuarioAgregado);
        registroUsuario.setFechaRegistro(new GetFecha().getFechaDateActual());
        return registroUsuario;
    }

    //___________________________________________________________________


    //metodos de ejecucion

    public void cargar(){
        //cargarImgDefault();
        cargarItemsComboEmpleado();

        // cargarItemsEdad();
    }

    public static Map<String, String> convertirListaAMap(List<Empleado> listaEmpleados) {
        return listaEmpleados.stream()
                .collect(Collectors.toMap(Empleado::getId,
                        empleado ->"("+empleado.getOcupacion()+") "+ empleado.getNombre() + " " + empleado.getAPaterno() + " " + empleado.getAMaterno()));
    }

    public void cargarItemsComboEmpleado(){


        List<Empleado> lista = empleadoDB.getEmpleadosSinUsuario();
        Map<String,String>mapa=convertirListaAMap(lista);
        List<String> listaId = empleadoDB.getIdEmpleadosSinUsuario();


        comboEmpleado.setItems(FXCollections.observableArrayList(listaId));

        comboEmpleado.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item + " - " + mapa.get(item)); // 📌 Muestra 'ID - Nombre'
                }
            }
        });
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

    @FXML public void actionSelectComboEmpleado(){
        Empleado empleadoTemporal = empleadoDB.getEmpleado(comboEmpleado.getValue().toString());
        String nombreUsuario= generarNombreUsuario(empleadoTemporal.getNombre(),empleadoTemporal.getAPaterno(),empleadoTemporal.getCurp());
        txtUsuario.setText(nombreUsuario);
    }

    //generar nombre de usuario
    public static String generarNombreUsuario(String nombre, String apellidoPaterno, String curp) {
        // 📌 Obtiene iniciales del nombre y apellido paterno
        String iniciales = (apellidoPaterno.substring(0, 2) + nombre.charAt(0)).toUpperCase();

        // 📌 Extrae un carácter del CURP para más singularidad
        String curpFragmento = curp.substring(curp.length() - 1).toUpperCase();

        // 📌 Genera un número aleatorio entre 0 y 9
        SecureRandom random = new SecureRandom();
        int numAleatorio = random.nextInt(10); // 📌 Un número entre 0 y 9

        // 📌 Concatenación final (5 caracteres únicos)
        return iniciales + curpFragmento + numAleatorio;
    }
}
