package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Embeddable
public class ServiciosMedicoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "medico_numregistromedico", referencedColumnName = "numregistromedico")
    private Medico id_medico;

    @ManyToOne
    @JoinColumn(name = "servicio_servicio_id", referencedColumnName = "idservicio")
    private ServicioSalud id_serviciosalud;

    
    public ServiciosMedicoPK(Medico id_medico, ServicioSalud id_serviciosalud) {
        this.id_medico = id_medico;
        this.id_serviciosalud = id_serviciosalud;
    }
    
    public ServiciosMedicoPK() {;}

    public Medico getId_medico() {
        return id_medico;
    }

    public void setId_medico(Medico id_medico) {
        this.id_medico = id_medico;
    }

    public ServicioSalud getId_serviciosalud() {
        return id_serviciosalud;
    }

    public void setId_serviciosalud(ServicioSalud id_serviciosalud) {
        this.id_serviciosalud = id_serviciosalud;
    }
}
