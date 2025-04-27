package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class DirectorioServicioPK implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "ips_nit", referencedColumnName = "nit")
    private Ips id_ips;

    @ManyToOne
    @JoinColumn(name = "serv_salud_serv_salud_id", referencedColumnName = "idservicio")
    private ServicioSalud id_serviciosalud;

    public DirectorioServicioPK(Ips id_ips, ServicioSalud id_serviciosalud) {
        super();
        this.id_ips = id_ips;
        this.id_serviciosalud = id_serviciosalud;
    }

    public DirectorioServicioPK() {;}

    public Ips getId_ips() {
        return id_ips;
    }

    public void setId_ips(Ips id_ips) {
        this.id_ips = id_ips;
    }

    public ServicioSalud getId_serviciosalud() {
        return id_serviciosalud;
    }

    public void setId_serviciosalud(ServicioSalud id_serviciosalud) {
        this.id_serviciosalud = id_serviciosalud;
    }
}
