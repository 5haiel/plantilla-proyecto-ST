package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "orden")
public class Orden {
    
    @EmbeddedId
    private OrdenPK pk;

    private LocalDate fechaemision;
    private String estado;

    public Orden(OrdenPK pk, LocalDate fechaemision, String estado) {
        this.pk = pk;
        this.fechaemision = fechaemision;
        this.estado = estado;
    }

    public Orden() {;}

    public OrdenPK getPk() {
        return pk;
    }

    public void setPk(OrdenPK pk) {
        this.pk = pk;
    }

    public LocalDate getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(LocalDate fechaemision) {
        this.fechaemision = fechaemision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
