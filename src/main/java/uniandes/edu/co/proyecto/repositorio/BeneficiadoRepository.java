package uniandes.edu.co.proyecto.repositorio;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Beneficiado;

public interface BeneficiadoRepository extends JpaRepository<Beneficiado, Integer> {

  @Query(value = "SELECT * FROM beneficiado", nativeQuery = true)
  Collection<Beneficiado> darTodosLosBeneficiados();

  @Query(value = "SELECT * FROM beneficiado WHERE idbeneficiario = :id", nativeQuery = true)
  Beneficiado darBeneficiado(@Param("id") Integer id);

  @Query(value = "SELECT * FROM beneficiado WHERE USUARIO_USUARIO_ID = :usuarioid", nativeQuery = true)
  Collection<Beneficiado> darBeneficiadosPorUsuario(@Param("usuarioid") Integer usuarioid);

  // RF 5: Agregar beneficiado
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO beneficiado (idbeneficiario, nombrebeneficiado, parentesco, usuario_usuario_id) VALUES (:idbeneficiario, :nombrebeneficiado, :parentesco, :usuarioid)", nativeQuery = true)
  void insertarBeneficiado(@Param("idbeneficiario") Integer idbeneficiario,
      @Param("nombrebeneficiado") String nombrebeneficiado, @Param("parentesco") String parentesco,
      @Param("usuarioid") Integer usuarioid);

  @Modifying
  @Transactional
  @Query(value = "UPDATE beneficiado SET nombrebeneficiado = :nombrebeneficiado, parentesco = :parentesco, USUARIO_USUARIO_ID = :usuarioid WHERE idbeneficiario = :id", nativeQuery = true)
  void actualizarBeneficiado(@Param("id") Integer id, @Param("nombrebeneficiado") String nombrebeneficiado,
      @Param("parentesco") String parentesco, @Param("usuarioid") Integer usuarioid);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM beneficiado WHERE idbeneficiario = :id", nativeQuery = true)
  void eliminarBeneficiado(@Param("id") Integer id);

}
