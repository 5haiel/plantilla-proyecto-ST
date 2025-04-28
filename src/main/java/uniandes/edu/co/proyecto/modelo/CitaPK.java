package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CitaPK implements Serializable {

    private Integer idcita;

    @ManyToOne
    @JoinColumn(name = "usuarioid", referencedColumnName = "usuarioid")
    private Usuario usuarioid;

    @ManyToOne
    @JoinColumn(name = "id_so", referencedColumnName = "id_so")
    private ServicioOrden servicioordenid;

    public CitaPK(Integer idcita, Usuario usuarioid, ServicioOrden servicioordenid) {
        super();
        this.idcita = idcita;
        this.usuarioid = usuarioid;
        this.servicioordenid = servicioordenid;
    }

    public CitaPK() {;}

    public Integer getIdcita() {
        return idcita;
    }

    public void setIdcita(Integer idcita) {
        this.idcita = idcita;
    }

    public Usuario getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Usuario usuarioid) {
        this.usuarioid = usuarioid;
    }

    public ServicioOrden getServicioordenid() {
        return servicioordenid;
    }

    public void setServicioordenid(ServicioOrden servicioordenid) {
        this.servicioordenid = servicioordenid;
    }

    
}
