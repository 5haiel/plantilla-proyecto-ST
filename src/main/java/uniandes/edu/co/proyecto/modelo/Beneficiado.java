package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "beneficiado")
public class Beneficiado {

  @Id
  @Column(name = "idbeneficiario")
  private Integer idbeneficiario;

  @Column(name = "nombrebeneficiado")
  private String nombrebeneficiado;

  @Column(name = "parentesco")
  private String parentesco;

  @ManyToOne
  @JoinColumn(name = "USUARIO_USUARIO_ID", referencedColumnName = "usuarioid")
  private Usuario usuarioBeneficiador;

  public Beneficiado(Integer idbeneficiario, String nombreBeneficiado, String parentesco, Usuario usuarioBeneficiador) {
    this.idbeneficiario = idbeneficiario;
    this.nombrebeneficiado = nombreBeneficiado;
    this.parentesco = parentesco;
    this.usuarioBeneficiador = usuarioBeneficiador;
  }

  public Beneficiado() {
    ;
  }

  public Integer getIdBeneficiario() {
    return idbeneficiario;
  }

  public String getParentesco() {
    return parentesco;
  }

  public Usuario getUsuarioBeneficiador() {
    return usuarioBeneficiador;
  }

  public void setIdBeneficiario(Integer idbeneficiario) {
    this.idbeneficiario = idbeneficiario;
  }

  public void setUsuarioBeneficiador(Usuario usuarioBeneficiador) {
    this.usuarioBeneficiador = usuarioBeneficiador;
  }

  public void setParentesco(String parentesco) {
    this.parentesco = parentesco;
  }

  public String getNombreBeneficiado() {
    return nombrebeneficiado;
  }

  public void setNombrebeneficiado(String nombrebeneficiado) {
    this.nombrebeneficiado = nombrebeneficiado;
  }

}
