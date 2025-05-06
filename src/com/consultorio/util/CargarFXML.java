package com.consultorio.util;

import com.consultorio.controlador.Inicio;
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
import com.consultorio.controlador.pagoYfacturacion.RegistrarPagos;
import com.consultorio.controlador.reportes.ExportarDatos;
import com.consultorio.controlador.reportes.ReportesAtencionMedica;
import com.consultorio.controlador.reportes.ReportesFinancieros;
import com.consultorio.controlador.usuarioPersonal.AgregarPersonal;
import com.consultorio.controlador.usuarioPersonal.MedicosAsistentes;
import com.consultorio.controlador.usuarioPersonal.PersonalGeneral;
import com.consultorio.controlador.usuarioPersonal.RolesPermisos;
import com.consultorio.modelo.personal.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;

public class CargarFXML {
    Connection connection;
    public Usuario usuario;
    //agregar usuario
    public void setUsuario(Usuario usuario){ this.usuario=usuario; }
    public void setConector(Connection connection){
        this.connection=connection;
        System.out.println("Conector en "+ this);
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

    public <T> void updateContenidoAnchorPane(String ruta, Class<T> tipoControlador , AnchorPane rootPanel){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent nuevoContenido = loader.load();
            T controlador =loader.getController();
            getControladorConection(controlador,tipoControlador);

            rootPanel.getChildren().clear();
            rootPanel.getChildren().add(nuevoContenido);
            for (Node nodo : rootPanel.getChildren()) {
                AnchorPane.setTopAnchor(nodo, 0.0);
                AnchorPane.setBottomAnchor(nodo, 0.0);
                AnchorPane.setLeftAnchor(nodo, 0.0);
                AnchorPane.setRightAnchor(nodo, 0.0);
            }

            //cargar los datos de la base de datos

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERROR AL CARGAR FXML "+e.getMessage()+"  ERROR: "+ e);
        }

    }

}
