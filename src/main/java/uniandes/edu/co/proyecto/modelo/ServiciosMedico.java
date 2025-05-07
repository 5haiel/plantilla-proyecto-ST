package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicios_medico")
public class ServiciosMedico {

    @EmbeddedId
    private ServiciosMedicoPK pk;

    public ServiciosMedico (Medico medico, ServicioSalud servicioSalud){
        this.pk = new ServiciosMedicoPK(medico, servicioSalud);
    }

    public ServiciosMedico () {;}

    public ServiciosMedicoPK getPk() {
        return pk;
    }

    public void setPk(ServiciosMedicoPK pk) {
        this.pk = pk;
    }
    
}
