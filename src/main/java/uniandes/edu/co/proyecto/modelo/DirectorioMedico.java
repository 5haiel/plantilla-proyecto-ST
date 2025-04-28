package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "directorio_medico")
public class DirectorioMedico {
  @EmbeddedId
  private DirectorioMedicoPK pk;

  public DirectorioMedico(Medico numregistromedico, Ips nit) {
    this.pk = new DirectorioMedicoPK(numregistromedico, nit);
  }

  public DirectorioMedico() {
    ;
  }

  public DirectorioMedicoPK getPk() {
    return pk;
  }

  public void setPk(DirectorioMedicoPK pk) {
    this.pk = pk;
  }

}