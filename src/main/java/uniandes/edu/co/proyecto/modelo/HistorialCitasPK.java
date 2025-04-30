package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Embeddable
public class HistorialCitasPK {

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_cita", referencedColumnName = "idcita"),
        @JoinColumn(name = "id_usuario", referencedColumnName = "usuarioid"),
        @JoinColumn(name = "id_orden", referencedColumnName = "id_so")
    })
    private Cita citaId;

    @ManyToOne
    @JoinColumn(name = "ips_nit", referencedColumnName = "nit")
    private Ips ipsId;

    public HistorialCitasPK() {}

    public HistorialCitasPK(Cita citaId, Ips ipsId) {
        this.citaId = citaId;
        this.ipsId = ipsId;
    }

    public Cita getCitaId() {
        return citaId;
    }

    public void setCitaId(Cita citaId) {
        this.citaId = citaId;
    }

    public Ips getIpsId() {
        return ipsId;
    }

    public void setIpsId(Ips ipsId) {
        this.ipsId = ipsId;
    }
}