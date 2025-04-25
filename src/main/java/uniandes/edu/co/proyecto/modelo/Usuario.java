package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    private Integer usuarioid;
    private String tipodocumento;
    private Integer numdocumento;
    private String nombre;
    private LocalDate fechanacimiento;
    private Integer telefono;
    private String tipocontribucion;

    public Usuario(Integer numdocumento, String tipodocumento, String nombre, LocalDate fechanacimiento, Integer telefono, String tipocontribucion, Integer usuarioid) {
        this.numdocumento = numdocumento;
        this.tipodocumento = tipodocumento;
        this.nombre = nombre;
        this.fechanacimiento = fechanacimiento;
        this.telefono = telefono;
        this.tipocontribucion = tipocontribucion;
        this.usuarioid = usuarioid;
    }

    public Usuario() {;}

    public Integer getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(Integer numdocumento) {
        this.numdocumento = numdocumento;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getTipocontribucion() {
        return tipocontribucion;
    }

    public void setTipocontribucion(String tipocontribucion) {
        this.tipocontribucion = tipocontribucion;
    }

    public Integer getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Integer usuarioid) {
        this.usuarioid = usuarioid;
    }
    
}
