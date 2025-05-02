package com.consultorio.controlador.pacientes;

import com.consultorio.modelo.clientes.Paciente;
import com.consultorio.modelo.clientes.RegistroPaciente;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.GetFecha;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.conection.modeloDataBase.clientes.PacienteDB;
import com.consultorio.util.conection.modeloDataBase.clientes.RegistroPacienteDB;
import com.consultorio.util.alertas.errores.VentanaErrores;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


public class RegistrarNuevoPaciente {

    //agregar un objeto de error
    VentanaErrores ventanaErrores = new VentanaErrores();
    String mensajeError="";

    AlertaAprobacion alertaAprobacion= new AlertaAprobacion();
    String foto="";
    File fileFoto=null;




    PacienteDB pacienteDB = new PacienteDB();
    RegistroPacienteDB registroPacienteDB = new RegistroPacienteDB();




    ///ID DE DATOS DE PACIENTE
    @FXML
    TextField tfNombre;
    @FXML
    TextField tfCurp;
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
           TextField tfEmail;




    String dirImage="";
//AQUI SE AGREGARAN LOS ITEMS
    @FXML
    ImageView ivFoto;
    @FXML
    ImageView ivFotoCamera;
    @FXML
    Button btnCargarImg;
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

    public void cargarBDPaciente(){
        pacienteDB.setConnection(connection);

    }



    @FXML
    public void initialize() {

        cargar();

        Platform.runLater(()->{
            //cargar todo lo demas
            pacienteDB.setConnection(connection);
            registroPacienteDB.setConnection(connection);

        });

    }

    //cargar funciones action

    @FXML
    public void actionRegistrar(){



        System.out.println("Entrar dentro de la funcion ejecutar");


        try {
            Paciente paciente=new Paciente();
            pacienteDB.setPaciente(cargarPaciente(paciente));
            registroPacienteDB.setRegistroPaciente( cargarRegistroPaciente(new RegistroPaciente(),paciente));

            alertaAprobacion.ventanaAprobacion("Registro del Paciente Exitoso");

            tfNombre.clear();
            tfCurp.clear();
            tfApellidoPaterno.clear();
            tfApellidoMaterno.clear();
            dpFechaNacimiento.setValue(null);
            tfDireccion.clear();
            tfTelefono.clear();
            comboTipoSexo.getSelectionModel().clearSelection();
            comboTipoSexo.setPromptText("Ingrese Sexo");
            tfEmail.clear();

            dirImage="/resource/img/user_unknown.jpg";
            ivFoto.setImage(new Image(getClass().getResource(dirImage).toExternalForm()));





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

    public Paciente cargarPaciente(Paciente paciente){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");






        String nombre=tfNombre.getText();
        String curp=(tfCurp.getText());
        String apellido_paterno=tfApellidoPaterno.getText();
        String apellido_materno=tfApellidoMaterno.getText();
        String fecha=dpFechaNacimiento.getValue().toString();
        LocalDate.parse(fecha,formato);
        String direccion=tfDireccion.getText();
        String telefono=tfTelefono.getText();
        String sexo=comboTipoSexo.getValue().toString();
        // resta de fecha actual - fecha nacimiento para edad
        String edad = String.valueOf(
                ChronoUnit.YEARS.between(dpFechaNacimiento.getValue(), LocalDate.now()) // Calcular edad

        );
        String email=tfEmail.getText();


        if (fileFoto==null) foto="";
        else {

            String rutaDestinoFoto="src/resource/img/pacientes/";
            File carpetaDestino= new File(rutaDestinoFoto);
            if (!carpetaDestino.exists()) carpetaDestino.mkdir();

            if (fileFoto!=null) {

                String getIdPaciente=curp;//llamar a base de datos
                //if (pacienteDB.obtenerUltimoIdPaciente()==null) getIdPaciente="1";
               // else getIdPaciente=String.valueOf(Integer.parseInt(pacienteDB.obtenerUltimoIdPaciente())+1);


                File destino = new File(carpetaDestino,"paciente_"+getIdPaciente);
                try {
                    Files.copy(fileFoto.toPath(),destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    foto=destino.getAbsolutePath();
                    System.out.println("Foto guardado con exito");

                }catch (Exception e){
                    ventanaErrores.ventanaErrorClasico("Error al guardar foto");
                }
            }

        }



        System.out.println(nombre);
        System.out.println(curp);
        System.out.println(apellido_paterno);
        System.out.println(apellido_materno);
        System.out.println(fecha);
        System.out.println(direccion);
        System.out.println(telefono);
        System.out.println(sexo);
        System.out.println(edad);
        System.out.println(email);
        System.out.println(foto);
        System.out.println();

        paciente.setNombre(nombre);
        paciente.setCurp(curp);
        paciente.setAPaterno(apellido_paterno);
        paciente.setAMaterno(apellido_materno);
        paciente.setFnacimiento(Date.valueOf(dpFechaNacimiento.getValue()));
        paciente.setDireccion(direccion);
        paciente.setTelefono(telefono);
        paciente.setSexo(sexo);
        paciente.setEdad(edad);
        paciente.setEmail(email);
        paciente.setFoto(foto);



        return paciente;
    }

    public RegistroPaciente cargarRegistroPaciente(RegistroPaciente registroPaciente, Paciente paciente){

        registroPaciente.setIdUsuario(Integer.parseInt(usuario.getId()));
        registroPaciente.setIdPaciente(Integer.parseInt(pacienteDB.obtenerUltimoIdPaciente()));
        registroPaciente.setFechaRegistro(new GetFecha().getFechaDateActual());

        return registroPaciente;
    }

    //___________________________________________________________________

    //CARGAR IMAGEN AL PRESIONAR BOTON
    @FXML
    public void actionbtnCamera(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona Imagen");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        //agregar variable al archivo

        fileFoto = fileChooser.showOpenDialog(null);

        if (fileFoto != null) {
            foto = fileFoto.toURI().toString();
            Image image = new Image(foto);
            ivFoto.setImage(image);
            System.out.println("Foto seleccionado");
            System.out.println("Ruta: "+foto);
           ivFoto.setPreserveRatio(true);
           ivFoto.setFitWidth(100);
           ivFoto.setFitHeight(100);
           ivFoto.setPreserveRatio(false);
           ivFoto.setSmooth(true);
        }



    }

    
    //metodos de ejecucion

    public void cargar(){
        cargarImgDefault();


       // cargarItemsEdad();
    }

    public void cargarImgDefault(){
        //llamar metodo DataBase

        dirImage=getDataBase(dirImage);



        if (Objects.equals(dirImage, "")) dirImage="/resource/img/user_unknown.jpg";


        ivFoto.setImage(new Image(getClass().getResource(dirImage).toExternalForm()));

        ivFoto.setFitWidth(100); // Ancho máximo
        ivFoto.setFitHeight(100); // Altura máxima
        ivFoto.setPreserveRatio(true); // Mantener la proporción
        ivFoto.setSmooth(true); // Suavizado para bordes más limpios
        ivFoto.setCache(true);


        //cambiar figura del boton
        btnCargarImg.setShape(new Circle(20));
        btnCargarImg.setMinSize(20,20);
        btnCargarImg.setMaxSize(30,30);
        //cargar imagen foto
        String dirImageFoto="/resource/img/camera.png";

        ivFotoCamera.setImage(new Image(getClass().getResource(dirImageFoto).toExternalForm()));
        ivFotoCamera.setFitWidth(20);
        ivFotoCamera.setFitHeight(20);
        ivFotoCamera.setPreserveRatio(true);
        ivFotoCamera.setSmooth(true);
        ivFotoCamera.setCache(true);


        //gridPane.setAlignment(btnCargarImg, Pos.BOTTOM_RIGHT);






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
