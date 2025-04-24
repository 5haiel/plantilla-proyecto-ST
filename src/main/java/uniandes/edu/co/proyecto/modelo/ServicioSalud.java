package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicio_salud")
public class ServicioSalud {
    @Id
    private Integer idservicio;

    private String tiposervicio;

    private String descripcion;

    private Integer servicio_salud_id;

    public ServicioSalud(Integer idServicio, String tipoServicio, String descripcion, Integer servicio_salud_id) {
        this.idservicio = idServicio;
        this.tiposervicio = tipoServicio;
        this.descripcion = descripcion;
        this.servicio_salud_id = servicio_salud_id;
    }

    public ServicioSalud() {;}

    public Integer getIdServicio() {
        return idservicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idservicio = idServicio;
    }

    public String getTipoServicio() {
        return tiposervicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tiposervicio = tipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getServicioSaludId() {
        return servicio_salud_id;
    }

    public void setServicioSaludId(Integer servicio_salud_id) {
        this.servicio_salud_id = servicio_salud_id;
    }

}
