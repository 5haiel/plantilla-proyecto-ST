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
        @JoinColumn(name = "numregistromedico", referencedColumnName = "medico_numregistromedico"),
        @JoinColumn(name = "ips_nit", referencedColumnName = "ips_nit")
    })
    private DirectorioMedico directorioMedico;

    @ManyToOne
    @JoinColumn(name = "id_so", referencedColumnName = "id_so")
    private ServicioOrden servicioOrden;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "serv_salud_serv_salud_id", referencedColumnName = "serv_salud_serv_salud_id"),
        @JoinColumn(name = "id_ips", referencedColumnName = "ips_nit")
    })
    private DirectorioServicio directorioServicio;

    private LocalDate fecha;
    private String disponibilidad;

    public Agenda(Integer idagenda, DirectorioMedico directorioMedico, ServicioOrden servicioOrden, DirectorioServicio directorioServicio, LocalDate fecha, String disponibilidad) {
        this.idagenda = idagenda;
        this.directorioMedico = directorioMedico;
        this.servicioOrden = servicioOrden;
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

    public ServicioOrden getServicioOrden() {
        return servicioOrden;
    }

    public void setServicioOrden(ServicioOrden servicioOrden) {
        this.servicioOrden = servicioOrden;
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
