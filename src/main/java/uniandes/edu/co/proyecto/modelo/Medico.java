package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico {
    @Id
    private Integer numregistromedico;

    private Integer numerodocumento;

    private String tipodocumento;

    private String especialidad;

    private String nombre;

    public Medico(Integer numregistromedico, Integer numerodocumento, String tipodocumento, String especialidad, String nombre) {
        this.numregistromedico = numregistromedico;
        this.numerodocumento = numerodocumento;
        this.tipodocumento = tipodocumento;
        this.especialidad = especialidad;
        this.nombre = nombre;
    }

    public Medico() {;}

    public Integer getNumRegistroMedico() {
        return numregistromedico;
    }

    public void setNumRegistroMedico(Integer numRegistroMedico) {
        this.numregistromedico = numRegistroMedico;
    }

    public Integer getNumeroDocumento() {
        return numerodocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numerodocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipodocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipodocumento = tipoDocumento;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
