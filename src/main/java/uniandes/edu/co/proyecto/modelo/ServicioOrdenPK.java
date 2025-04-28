package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ServicioOrdenPK implements Serializable {
    private Integer idservicioorden;

    public ServicioOrdenPK(Integer idservicioorden) {
        super();
        this.idservicioorden = idservicioorden;
    }

    public ServicioOrdenPK() {
        super();
    }

    public Integer getIdservicioorden() {
        return idservicioorden;
    }

    public void setIdservicioorden(Integer idservicioorden) {
        this.idservicioorden = idservicioorden;
    }
}
