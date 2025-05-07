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

    public interface RespuestaMostrarIndiceUsoServicios {
        Long idServicio();
        int indiceUso();
    }

    public interface RespuestaDar20ServiciosSalud {
        String tipoServicio();
        int vecesAgendado();
    }

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

     //RFC2
     @Query(value="SELECT SERVICIO_SALUD.tipoServicio, count(DIRECTORIO_SERVICIO.idServicio) as veces_agendado " +
     "FROM DIRECTORIO_SERVICIO"+
     "INNER JOIN SERVICIO_SALUD ON DIRECTORIO_SERVICIO.ID_SERVICIOSALUD = SERVICIO_SALUD.ID " +
     "INNER JOIN CITA ON DIRECTORIO_SERVICIO.idCita=CITA.id " +
     "WHERE CITA.fecha BETWEEN :fechaInicial AND :fechaFinal  " +
     "GROUP BY SERVICIO_SALUD.tipoServicio, SERVICIO_SALUD.ID ORDER BY  veces_agendado DESC " +
     "FETCH FIRST 20 ROWS ONLY"
     , nativeQuery = true)
    Collection<RespuestaDar20ServiciosSalud> dar20ServiciosSalud(@Param ("fechaInicial") String fechaInicial, @Param ("fechaFinal") String fechaFinal);

    //RFC3
    @Query(value="SELECT Servicio_Orden.idServicio," +
     "ROUND( COUNT(CITA.idServicio) /SELECT COUNT(*) FROM CITA WHERE fecha BETWEEN :fechaIn AND :fechaOut) , 2) AS indice_uso" +
     "FROM Servicio_Orden " +
     "INNER JOIN CITA ON CITA.idServicio = Servicio_Orden.idServicio " +
     "WHERE CITA.fecha BETWEEN :fechaIn AND :fechaOut" +
     "GROUP BY Servicio_Orden.idServicio" +
     "ORDER BY indice_uso DESC",
     nativeQuery = true)
    Collection<RespuestaMostrarIndiceUsoServicios> mostrarIndiceUsoServicios(@Param("fechaIn") String fechaIn, @Param("fechaOut") String fechaOut);

}
