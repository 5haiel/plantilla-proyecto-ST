package uniandes.edu.co.proyecto.repositorio;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Orden;
import uniandes.edu.co.proyecto.modelo.OrdenPK;

public interface OrdenRepository extends JpaRepository<Orden, OrdenPK> {

    @Query(value = "SELECT * FROM orden", nativeQuery = true)
    Collection<Orden> darAllOrdenes();

    @Query(value = "SELECT * FROM orden WHERE idorden= :idorden AND medico_numregistromedico = :numregistromedico AND usuario_usuario_id = :usuarioid", nativeQuery = true)
    Orden darOrden(@Param("idorden") Integer idorden, @Param("numregistromedico") Integer numregistromedico, @Param("usuarioid") Integer usuarioid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO orden (idorden, medico_numregistromedico, usuario_usuario_id, fechaemision, estado) VALUES (:idorden, :numregistromedico, :usuarioid, :fechaemision, :estado)", nativeQuery = true)
    void insertarOrden(@Param("idorden") Integer idorden, @Param("numregistromedico") Integer numregistromedico, @Param("usuarioid") Integer usuarioid, @Param("fechaemision") LocalDate fechaemision, @Param("estado") String estado);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orden SET fechaemision = :fechaemision, estado = :estado WHERE idorden = :idorden AND medico_numregistromedico = :numregistromedico AND usuario_usuario_id = :usuarioid", nativeQuery = true)
    void actualizarOrden(@Param("idorden") Integer idorden, @Param("numregistromedico") Integer numregistromedico, @Param("usuarioid") Integer usuarioid, @Param("fechaemision") LocalDate fechaemision, @Param("estado") String estado);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM orden WHERE idorden = :idorden AND medico_numregistromedico = :numregistromedico AND usuario_usuario_id = :usuarioid", nativeQuery = true)
    void eliminarOrden(@Param("idorden") Integer idorden, @Param("numregistromedico") Integer numregistromedico, @Param("usuarioid") Integer usuarioid);

}
