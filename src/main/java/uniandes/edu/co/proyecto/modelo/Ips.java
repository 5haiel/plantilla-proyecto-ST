package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ips")
public class Ips {
    @Id
    private Integer NIT;

    private String nombre;

    private String tipo;

    private String direccion;

    private Integer telefono;

    public Ips(Integer NIT, String nombre, String tipo, String direccion, Integer telefono) {
        this.NIT = NIT;
        this.nombre = nombre;
        this.tipo = tipo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Ips() {;}

    public Integer getNIT() {
        return NIT;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setNIT(Integer Nit) {
        this.NIT = Nit;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    
}
