package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicioorden")
public class ServicioOrden {

    @EmbeddedId
    private ServicioOrdenPK pk;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id", referencedColumnName = "idorden"),
        @JoinColumn(name = "numregistromedico", referencedColumnName = "numregistromedico"),
        @JoinColumn(name = "usuarioid", referencedColumnName = "usuarioid")
    })
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "idservicio", referencedColumnName = "idservicio")
    private ServicioSalud serviciosalud;

    public ServicioOrden(ServicioOrdenPK pk, Orden orden, ServicioSalud serviciosalud) {
        this.pk = pk;
        this.orden = orden;
        this.serviciosalud = serviciosalud;
    }

    public ServicioOrden() {;}

    public ServicioOrdenPK getPk() {
        return pk;
    }

    public void setPk(ServicioOrdenPK pk) {
        this.pk = pk;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public ServicioSalud getServiciosalud() {
        return serviciosalud;
    }

    public void setServiciosalud(ServicioSalud serviciosalud) {
        this.serviciosalud = serviciosalud;
    }

    
}
