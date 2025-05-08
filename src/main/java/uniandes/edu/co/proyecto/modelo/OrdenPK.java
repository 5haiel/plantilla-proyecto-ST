package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrdenPK implements Serializable {
    
    private Integer idorden;

    @ManyToOne
    @JoinColumn(name = "medico_numregistromedico", referencedColumnName = "numregistromedico")
    private Medico medicoid;

    @ManyToOne
    @JoinColumn(name = "usuario_usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuarioid;

    public OrdenPK(Integer idorden, Medico medicoid, Usuario usuarioid) {
        super();
        this.idorden = idorden;
        this.medicoid = medicoid;
        this.usuarioid = usuarioid;
    }

    public OrdenPK() {;}

    public Integer getIdorden() {
        return idorden;
    }

    public void setIdorden(Integer idorden) {
        this.idorden = idorden;
    }

    public Medico getMedicoid() {
        return medicoid;
    }

    public void setMedicoid(Medico medicoid) {
        this.medicoid = medicoid;
    }

    public Usuario getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Usuario usuarioid) {
        this.usuarioid = usuarioid;
    }

    
}
