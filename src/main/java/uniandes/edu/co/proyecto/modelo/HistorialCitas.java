package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "historial_citas")
public class HistorialCitas {

    @EmbeddedId
    private HistorialCitasPK PK;

    public HistorialCitas() {}

    public HistorialCitas(Cita citaId, Ips ipsId) {
        this.PK = new HistorialCitasPK(citaId, ipsId);
    }

    public HistorialCitasPK getPK() {
        return PK;
    }
    public void setPK(HistorialCitasPK PK) {
        this.PK = PK;
    }
    public Cita getCitaId() {
        return PK.getCitaId();
    }
    public void setCitaId(Cita citaId) {
        this.PK.setCitaId(citaId);
    }
    public Ips getIpsId() {
        return PK.getIpsId();
    }
    public void setIpsId(Ips ipsId) {
        this.PK.setIpsId(ipsId);
    }

}