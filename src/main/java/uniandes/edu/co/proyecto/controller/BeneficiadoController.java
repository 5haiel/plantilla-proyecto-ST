package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Beneficiado;
import uniandes.edu.co.proyecto.repositorio.BeneficiadoRepository;

@RestController
public class BeneficiadoController {
  @Autowired
  private BeneficiadoRepository beneficiadoRepository;

  @GetMapping("/beneficiados")
  public Collection<Beneficiado> beneficiados() {
    return beneficiadoRepository.darTodosLosBeneficiados();
  }

  @GetMapping("/beneficiados/{id}")
  public Beneficiado obtenerBeneficiado(@PathVariable("id") Integer id) {
    return beneficiadoRepository.darBeneficiado(id);
  }

  @GetMapping("/usuarios/{usuarioid}/beneficiados")
  public Collection<Beneficiado> listarBeneficiadosPorUsuario(@PathVariable("usuarioid") Integer usuarioid) {
    return beneficiadoRepository.darBeneficiadosPorUsuario(usuarioid);
  }

  @PostMapping("/beneficiados/new/save")
  public String guardarBeneficiado(@RequestBody Beneficiado beneficiado) {
    beneficiadoRepository.insertarBeneficiado(
        beneficiado.getIdBeneficiario(),
        beneficiado.getNombreBeneficiado(),
        beneficiado.getParentesco(),
        beneficiado.getUsuarioBeneficiador().getUsuarioid());
    return "Beneficiado registrado exitosamente.";
  }

  @PostMapping("/beneficiados/{id}/edit/save")
  public String editarBeneficiado(@PathVariable("id") Integer id, @RequestBody Beneficiado beneficiado) {
    beneficiadoRepository.actualizarBeneficiado(
        id,
        beneficiado.getNombreBeneficiado(),
        beneficiado.getParentesco(),
        beneficiado.getUsuarioBeneficiador().getUsuarioid());
    return "Beneficiado actualizado exitosamente.";
  }

  @DeleteMapping("/beneficiados/{id}/delete")
  public String eliminarBeneficiado(@PathVariable("id") Integer id) {
    beneficiadoRepository.eliminarBeneficiado(id);
    return "Beneficiado eliminado exitosamente.";
  }
}
