package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.DirectorioServicio;
import uniandes.edu.co.proyecto.modelo.DirectorioServicioPK;

public interface DirectorioServicioRepository extends JpaRepository<DirectorioServicio, DirectorioServicioPK> {

    @Query(value = "SELECT * FROM directorio_servicio", nativeQuery = true)
    Collection<DirectorioServicio> darAllDirectorioServicio();

    @Query(value = "SELECT * FROM directorio_servicio WHERE ips_nit = :id_ips AND serv_salud_serv_salud_id = :id_serviciosalud", nativeQuery = true)
    DirectorioServicio darDirectorioServicio(@Param("id_ips") Integer id_ips, @Param("id_serviciosalud") Integer id_serviciosalud);
    
    @Query(value = "SELECT * FROM directorio_servicio WHERE ips_nit = :id_ips", nativeQuery = true)
    Collection<DirectorioServicio> darDirectorioServicioPorIps(@Param("id_ips") Integer id_ips);

    @Query(value = "SELECT * FROM directorio_servicio WHERE serv_salud_serv_salud_id = :id_serviciosalud", nativeQuery = true)
    Collection<DirectorioServicio> darDirectorioServicioPorServicio(@Param("id_serviciosalud") Integer id_serviciosalud);

    // RF3: Asignar un servicio de salud a una IPS
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO directorio_servicio (ips_nit, serv_salud_serv_salud_id) VALUES (:id_ips, :id_serviciosalud)", nativeQuery = true)
    void insertarDirectorioServicio(@Param("id_ips") Integer id_ips, @Param("id_serviciosalud") Integer id_serviciosalud);

    @Modifying
    @Transactional
    @Query(value = "UPDATE directorio_servicio SET ips_nit = :id_ips, serv_salud_serv_salud_id = :id_serviciosalud WHERE id_ips = :id_ips AND id_serviciosalud = :id_serviciosalud", nativeQuery = true)
    void actualizarDirectorioServicio(@Param("id_ips") Integer id_ips, @Param("id_serviciosalud") Integer id_serviciosalud);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM directorio_servicio WHERE ips_nit = :id_ips AND serv_salud_serv_salud_id = :id_serviciosalud", nativeQuery = true)
    void eliminarDirectorioServicio(@Param("id_ips") Integer id_ips, @Param("id_serviciosalud") Integer id_serviciosalud);

}
