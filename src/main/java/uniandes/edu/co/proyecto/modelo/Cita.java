package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cita")
public class Cita {
    
    @EmbeddedId
    private CitaPK pk;

    private String estado;
    private LocalDate fecha;
    private String disponibilidad;

    public Cita(CitaPK pk, String estado, LocalDate fecha, String disponibilidad) {
        this.pk = pk;
        this.estado = estado;
        this.fecha = fecha;
        this.disponibilidad = disponibilidad;
    }

    public Cita() {;}

    public CitaPK getPk() {
        return pk;
    }

    public void setPk(CitaPK pk) {
        this.pk = pk;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
