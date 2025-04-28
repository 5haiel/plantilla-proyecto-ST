package uniandes.edu.co.proyecto.repositorio;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Cita;
import uniandes.edu.co.proyecto.modelo.CitaPK;

public interface CitaRepository extends JpaRepository<Cita, CitaPK> {
    
    @Query(value = "SELECT * FROM cita", nativeQuery = true)
    Collection<Cita> darAllCitas();

    @Query(value = "SELECT * FROM cita WHERE idcita = :idcita AND servicio_orden_id = :servicioordenid AND usuario_usuario_id = :usuarioid", nativeQuery = true)
    Cita darCita(@Param("idcita") Integer idcita, @Param("servicioordenid") Integer servicioordenid, @Param("usuarioid") Integer usuarioid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cita (idcita, servicio_orden_id, usuario_usuario_id, estado, fecha, disponibilidad) VALUES (:idcita, :servicioordenid, :usuarioid, :estado, :fecha, :disponibilidad)", nativeQuery = true)
    void insertarCita(@Param("idcita") Integer idcita, @Param("servicioordenid") Integer servicioordenid, @Param("usuarioid") Integer usuarioid, @Param("estado") String estado, @Param("fecha") LocalDate fecha, @Param("disponibilidad") String disponibilidad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cita SET servicio_orden_id = :servicioordenid, usuario_usuario_id = :usuarioid, estado = :estado, fecha = :fecha, disponibilidad = :disponibilidad WHERE idcita = :idcita", nativeQuery = true)
    void actualizarCita(@Param("idcita") Integer idcita, @Param("servicioordenid") Integer servicioordenid, @Param("usuarioid") Integer usuarioid, @Param("estado") String estado, @Param("fecha") LocalDate fecha, @Param("disponibilidad") String disponibilidad);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cita WHERE idcita = :idcita AND servicio_orden_id = :servicioordenid AND usuario_usuario_id = :usuarioid", nativeQuery = true)
    void eliminarCita(@Param("idcita") Integer idcita, @Param("servicioordenid") Integer servicioordenid, @Param("usuarioid") Integer usuarioid);

}   
