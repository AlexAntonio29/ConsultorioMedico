package com.consultorio.controlador.citas;

import com.consultorio.controlador.configuracionEstructura.AgregarConsultorio;
import com.consultorio.modelo.clientes.Paciente;
import com.consultorio.modelo.estructura.Consultorio;
import com.consultorio.modelo.estructura.Edificio;
import com.consultorio.modelo.estructura.RegistroConsultorio;
import com.consultorio.modelo.movimiento.movimientoMedico.Cita;
import com.consultorio.modelo.movimiento.movimientoMedico.RegistroCita;
import com.consultorio.modelo.personal.Usuario;
import com.consultorio.util.CargarFXML;
import com.consultorio.util.GetFecha;
import com.consultorio.util.alertas.AlertaAprobacion;
import com.consultorio.util.alertas.errores.VentanaErrores;
import com.consultorio.util.conection.modeloDataBase.clientes.PacienteDB;
import com.consultorio.util.conection.modeloDataBase.estructura.ConsultorioDB;
import com.consultorio.util.conection.modeloDataBase.movimiento.movimientoMedico.CitaDB;
import com.consultorio.util.conection.modeloDataBase.movimiento.movimientoMedico.atencion.RegistroCitaDB;
import com.consultorio.util.conection.modeloDataBase.personal.UsuarioDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class AgendarNuevaCita {
    public AgendarNuevaCita(){}

    @FXML
    AnchorPane rootPanel;
    String ruta="/com/consultorio/vista/citas/agendar_nueva_cita.fxml";
    CargarFXML cargarFXML= new CargarFXML();


    //agregar un objeto de error
    VentanaErrores ventanaErrores = new VentanaErrores();
    String mensajeError="";


    AlertaAprobacion alertaAprobacion= new AlertaAprobacion();



   // public EdificioDB edificioDB= new EdificioDB();
    CitaDB citaDB = new CitaDB();
    RegistroCitaDB registroCitaDB = new RegistroCitaDB();

    UsuarioDB usuarioDB = new UsuarioDB();
    PacienteDB pacienteDB = new PacienteDB();
    ConsultorioDB consultorioDB= new ConsultorioDB();




    ///ID DE DATOS DE Edificio

    @FXML
    ComboBox comboPaciente;
    @FXML
    DatePicker dpfechaCita;
    @FXML
    ComboBox comboHora;
    @FXML
    ComboBox comboDoctor;
    @FXML ComboBox comboConsultorio;
    @FXML
    TextField txtMotivo;






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
        citaDB.setConnection(connection);

    }



    @FXML
    public void initialize() {



        Platform.runLater(()->{
            //cargar todo lo demas
           // edificioDB.setConnection(connection);
            citaDB.setConnection(connection);
            registroCitaDB.setConnection(connection);

            cargarFXML.setConector(connection);
            cargarFXML.setUsuario(usuario);

            usuarioDB.setConector(connection);
            usuarioDB.setUsuario(usuario);

            pacienteDB.setConnection(connection);
            consultorioDB.setConnection(connection);
            cargar();

        });

    }

    //cargar funciones action

    @FXML
    public void actionRegistrar(){



        System.out.println("Entrar dentro de la funcion ejecutar");


        try {



            Cita cita=new Cita();
            citaDB.setCita(cargarCita(cita));
            registroCitaDB.setRegistroCita( cargarRegistroCita(new RegistroCita()));


           /*tfNumConsultorio.clear();
            cbEspecialidad.setValue(null);
            cbEspecialidad.setPromptText("Ingrese Especialidad");
            cbIdEdificio.setValue(null);
            cbIdEdificio.setPromptText("Ingrese ID de Edificio");
            tfNumPiso.clear();

            List<String> listaId = edificioDB.getIdEdificios();
            cbIdEdificio.setItems(FXCollections.observableArrayList(listaId));

            */

            cargarFXML.updateContenidoAnchorPane(ruta, AgendarNuevaCita.class,rootPanel);






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

    public Cita cargarCita(Cita cita){
        Paciente paciente= pacienteDB.getPaciente(Integer.parseInt(comboPaciente.getValue().toString()));
        Date fecha= java.sql.Date.valueOf(dpfechaCita.getValue());
        LocalTime hora= LocalTime.parse(comboHora.getValue().toString());
        Usuario doctor = usuarioDB.getUsuario(comboDoctor.getValue().toString());//doctor
        Consultorio consultorio= consultorioDB.getConsultorio(comboConsultorio.getValue().toString());
        String motivo= txtMotivo.getText();




        cita.setPaciente(paciente);
        cita.setFecha(fecha);
        cita.setHora(hora);
        cita.setUsuario(doctor);
        cita.setConsultorio(consultorio);
        cita.setMotivo(motivo);



        return cita;
    }

    public RegistroCita cargarRegistroCita(RegistroCita registroCita){

        String user=usuario.getId();
        String idCita = citaDB.obtenerUltimoIdCita();
        String fecha=new GetFecha().getFechaStringActual();

        registroCita.setIdUsuario(user);
        registroCita.setIdCita(idCita);
        registroCita.setFechaRegistro(new GetFecha().getFechaDateActual());
        return registroCita;
    }

    //___________________________________________________________________


    //metodos de ejecucion

    public void cargar(){
        //cargarImgDefault();
        cargarItemsComboBoxIDEdificio();

        // cargarItemsEdad();
    }
    public void cargarItemsComboBoxIDEdificio(){

        List<Paciente> pacientes= pacienteDB.getPacientes();
        List<Consultorio> consultorios= consultorioDB.getConsultorios();
        List<Usuario> doctores= usuarioDB.getUsuariosMedicos();

        //List<Edificio> lista = edificioDB.getEdificios();

        List<String> listaIdPacientes =pacienteDB.getIdPacientesIdString();
        List<String> listaIdConsultorio = consultorioDB.getIdConsultoriosIdString();
        List<String> listaIdDoctores = usuarioDB.getUsuariosMedicosString();

        comboPaciente.setItems(FXCollections.observableArrayList(listaIdPacientes));
        comboConsultorio.setItems(FXCollections.observableArrayList(listaIdConsultorio));
        comboDoctor.setItems(FXCollections.observableArrayList(listaIdDoctores));

        //comboHora.setItems(FXCollections.observableArrayList(listaId));
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
