package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "agenda")
public class Agenda {
    
    @Id
    private Integer idagenda;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "directorio_medico_regmed", referencedColumnName = "medico_numregistromedico"),
        @JoinColumn(name = "directorio_medico_nit", referencedColumnName = "ips_nit")
    })
    private DirectorioMedico directorioMedico;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "servicio_medico_serv_id", referencedColumnName = "servicio_servicio_id"),
        @JoinColumn(name = "servicio_medico_regmed", referencedColumnName = "medico_numregistromedico")
    })
    private ServiciosMedico serviciosMedico;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "directorio_servicio_serv_id", referencedColumnName = "serv_salud_serv_salud_id"),
        @JoinColumn(name = "directorio_servicio_nit", referencedColumnName = "ips_nit")
    })
    private DirectorioServicio directorioServicio;

    private LocalDate fecha;
    private String disponibilidad;

    public Agenda(Integer idagenda, DirectorioMedico directorioMedico, ServiciosMedico serviciosMedico, DirectorioServicio directorioServicio, LocalDate fecha, String disponibilidad) {
        this.idagenda = idagenda;
        this.directorioMedico = directorioMedico;
        this.serviciosMedico = serviciosMedico;
        this.directorioServicio = directorioServicio;
        this.fecha = fecha;
        this.disponibilidad = disponibilidad;
    }

    public Agenda() {;}

    public Integer getIdagenda() {
        return idagenda;
    }

    public void setIdagenda(Integer idagenda) {
        this.idagenda = idagenda;
    }

    public DirectorioMedico getDirectorioMedico() {
        return directorioMedico;
    }

    public void setDirectorioMedico(DirectorioMedico directorioMedico) {
        this.directorioMedico = directorioMedico;
    }

    public ServiciosMedico getServiciosMedico() {
        return serviciosMedico;
    }

    public void setServiciosMedico(ServiciosMedico serviciosMedico) {
        this.serviciosMedico = serviciosMedico;
    }

    public DirectorioServicio getDirectorioServicio() {
        return directorioServicio;
    }

    public void setDirectorioServicio(DirectorioServicio directorioServicio) {
        this.directorioServicio = directorioServicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    
}
