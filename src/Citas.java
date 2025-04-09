import java.time.LocalTime;
import java.util.Date;

public class Citas {

    Doctor idDoctor;
    Paciente idPaciente;
    Date fecha;
    LocalTime hora;
    Consultorio nConsultorio;

    public Doctor getIdDoctor() {
        return idDoctor;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Consultorio getnConsultorio() {
        return nConsultorio;
    }

    public void setIdDoctor(Doctor idDoctor) {
        this.idDoctor = idDoctor;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setnConsultorio(Consultorio nConsultorio) {
        this.nConsultorio = nConsultorio;
    }
}
