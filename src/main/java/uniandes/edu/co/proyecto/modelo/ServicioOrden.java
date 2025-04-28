package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicio_orden")
public class ServicioOrden {

    @Id
    @Column(name = "id_so")
    private Integer pk;

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

    public ServicioOrden(Integer pk, Orden orden, ServicioSalud serviciosalud) {
        this.pk = pk;
        this.orden = orden;
        this.serviciosalud = serviciosalud;
    }

    public ServicioOrden() {;}

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
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
