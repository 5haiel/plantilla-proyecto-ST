package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.ServicioOrden;

public interface ServicioOrdenRepository extends JpaRepository<ServicioOrden, Integer> {

    @Query(value = "SELECT * FROM servicio_orden", nativeQuery = true)
    Collection<ServicioOrden> darAllServicioOrdenes();

    @Query(value = "SELECT * FROM servicio_orden WHERE id_so = :idservicioorden", nativeQuery = true)
    ServicioOrden darServicioOrdenPorId(@Param("idservicioorden") Integer idservicioorden);

    @Query(value = "SELECT * FROM servicio_orden WHERE orden_idorden = :id_orden", nativeQuery = true)
    Collection<ServicioOrden> darServicioOrdenPorIdOrden(@Param("id_orden") Integer id_orden);

    @Query(value = "SELECT * FROM servicio_orden WHERE orden_numregistromedico = :numregistromedico", nativeQuery = true)
    Collection<ServicioOrden> darServicioOrdenPorNumRegistroMedico(@Param("numregistromedico") Integer numregistromedico);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicio_orden (id_so, orden_idorden, orden_numregistromedico, serv_salud_serv_salud_id) VALUES (:idservicioorden, :idorden, :numregistromedico, :serviciosalud)", nativeQuery = true)
    void insertarServicioOrden(@Param("idservicioorden") Integer idservicioorden,@Param("idorden") Integer idorden,@Param("numregistromedico") Integer numregistromedico,@Param("serviciosalud") Integer serviciosalud);

    @Modifying
    @Transactional
    @Query(value = "UPDATE servicio_orden SET orden_idorden = :idorden, orden_numregistromedico = :numregistromedico, serv_salud_serv_salud_id = :serviciosalud WHERE id_so = :idservicioorden", nativeQuery = true)
    void actualizarServicioOrden(@Param("idservicioorden") Integer idservicioorden,@Param("idorden") Integer idorden,@Param("numregistromedico") Integer numregistromedico,@Param("serviciosalud") Integer serviciosalud);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicio_orden WHERE id_so = :idservicioorden", nativeQuery = true)
    void eliminarServicioOrden(@Param("idservicioorden") Integer idservicioorden);
}
