package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.ServicioSalud;

public interface ServicioSaludRepository extends JpaRepository<ServicioSalud, Long> {
    @Query(value = "SELECT * FROM servicio_salud", nativeQuery = true)
    Collection<ServicioSalud> darAllServicios();

    @Query(value = "SELECT * FROM servicio_salud WHERE idservicio = :id", nativeQuery = true)
    ServicioSalud darServicio(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicio_salud (idservicio, tiposervicio, descripcion, servicio_salud_id) VALUES (:idservicio, :tiposervicio, :descripcion, :servicio_salud_id)", nativeQuery = true)
    void insertarServicio(@Param("idservicio") Integer idservicio, @Param("tiposervicio") String tiposervicio, @Param("descripcion") String descripcion, @Param("servicio_salud_id") Integer servicio_salud_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE servicio_salud SET tiposervicio = :tiposervicio, descripcion = :descripcion, servicio_salud_id = :servicio_salud_id WHERE idservicio = :idservicio", nativeQuery = true)
    void actualizarServicio(@Param("idservicio") Integer idServicio, @Param("tiposervicio") String tipoServicio, @Param("descripcion") String descripcion, @Param("servicio_salud_id") Integer servicio_salud_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicio_salud WHERE idservicio = :id", nativeQuery = true)
    void eliminarServicio(@Param("id") Integer id);
}
