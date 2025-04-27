package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "directorio_servicio")
public class DirectorioServicio {
    @EmbeddedId
    private DirectorioServicioPK pk;

    public DirectorioServicio (Ips ips, ServicioSalud serviciosalud) {
        this.pk = new DirectorioServicioPK(ips, serviciosalud);
    }

    public DirectorioServicio() {;}

    public DirectorioServicioPK getPk() {
        return pk;
    }

    public void setPk(DirectorioServicioPK pk) {
        this.pk = pk;
    }
   
}
