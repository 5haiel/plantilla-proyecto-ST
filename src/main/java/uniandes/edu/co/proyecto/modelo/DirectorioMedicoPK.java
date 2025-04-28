package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class DirectorioMedicoPK implements Serializable {

  @ManyToOne
  @JoinColumn(name = "medico_numregistromedico", referencedColumnName = "numregistromedico")
  private Medico numregistromedico;

  @ManyToOne
  @JoinColumn(name = "ips_nit", referencedColumnName = "nit")
  private Ips nit;

  public DirectorioMedicoPK(Medico numregistromedico, Ips nit) {
    super();
    this.numregistromedico = numregistromedico;
    this.nit = nit;
  }

  public DirectorioMedicoPK() {
    ;
  }

  public void setNum_registromedico(Medico numregistromedico) {
    this.numregistromedico = numregistromedico;
  }

  public void setId_Ips(Ips nit) {
    this.nit = nit;
  }

  public Medico getNum_registromedico() {
    return numregistromedico;
  }

  public Ips getId_Ips() {
    return nit;
  }

}