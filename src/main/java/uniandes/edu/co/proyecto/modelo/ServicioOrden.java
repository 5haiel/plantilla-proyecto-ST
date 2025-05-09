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
    private Integer id_so;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id", referencedColumnName = "idorden"),
        @JoinColumn(name = "orden_numregistromedico", referencedColumnName = "medico_numregistromedico"),
        @JoinColumn(name = "usuario_usuario_id", referencedColumnName = "usuario_usuario_id")
    })
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "idservicio", referencedColumnName = "idservicio")
    private ServicioSalud serviciosalud;

    public ServicioOrden(Integer pk, Orden orden, ServicioSalud serviciosalud) {
        this.id_so = pk;
        this.orden = orden;
        this.serviciosalud = serviciosalud;
    }

    public ServicioOrden() {;}

    public Integer getPk() {
        return id_so;
    }

    public void setPk(Integer pk) {
        this.id_so = pk;
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
