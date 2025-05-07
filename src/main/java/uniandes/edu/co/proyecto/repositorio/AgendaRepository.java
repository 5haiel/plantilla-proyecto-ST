package uniandes.edu.co.proyecto.repositorio;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    @Query(value = "SELECT * FROM agenda", nativeQuery = true)
    Collection<Agenda> darAllAgendas();

    @Query(value = "SELECT * FROM agenda WHERE idagenda = :id", nativeQuery = true)
    Agenda darAgendaPorId(@Param("id") Integer id);

    // Candado
    @Query(value = "SELECT * FROM agenda WHERE idagenda = :id FOR UPDATE", nativeQuery = true)
    Agenda darAgendaPorIdCandado(@Param("id") Integer id);

    @Query(value = "SELECT * FROM agenda WHERE idagenda = :id AND fecha = :fecha", nativeQuery = true)
    Agenda darAgendaPorIdYFecha(@Param("id") Integer id, @Param("fecha") LocalDate fecha);

    // RF7: Agendar un servicio de salud por parte de un afiliado
    @Query(value = "SELECT * FROM agenda WHERE disponibilidad = 'Disponible' AND fecha BETWEEN :startDate AND :endDate", nativeQuery = true)
    Collection<Agenda> darAgendasPorRangoDeFechas(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT * FROM agenda WHERE disponibilidad = 'Disponible' AND directorio_servicio_serv_id = :idServicio AND fecha BETWEEN :startDate AND :endDate", nativeQuery = true)
    Collection<Agenda> darAgendasPorRangoDeFechasYServicio(@Param("idServicio") Integer idServicio, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Modifying
    @Transactional
    @Query(value = " UPDATE agenda SET fecha = :fecha, disponibilidad = :disponibilidad, directorio_servicio_nit = :directorio_servicio_nit, directorio_servicio_serv_id = :directorio_servicio_serv_id, directorio_medico_nit = :directorio_medico_nit, directorio_medico_regmed = :directorio_medico_regmed, servicio_medico_regmed = :servicio_medico_regmed, servicio_medico_serv_id = :servicio_medico_serv_id WHERE id = :id AND disponibilidad = 'Disponible' ", nativeQuery = true)
    void actualizarAgendaPorDisponibilidad(@Param("fecha") LocalDate fecha, @Param("disponibilidad") String disponibilidad, @Param("directorio_servicio_nit") Integer directorio_servicio_nit, @Param("directorio_servicio_serv_id") Integer directorio_servicio_serv_id, @Param("directorio_medico_nit") Integer directorio_medico_nit, @Param("directorio_medico_regmed") Integer directorio_medico_regmed, @Param("servicio_medico_regmed") Integer servicio_medico_regmed, @Param("servicio_medico_serv_id") Integer servicio_medico_serv_id, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO agenda (fecha, disponibilidad, directorio_servicio_nit, directorio_servicio_serv_id, directorio_medico_nit, directorio_medico_regmed, servicio_medico_regmed, servicio_medico_serv_id, idagenda) VALUES (:fecha, :disponibilidad, :directorio_servicio_nit, :directorio_servicio_serv_id, :directorio_medico_nit, :directorio_medico_regmed, :servicio_medico_regmed, :servicio_medico_serv_id, :id)", nativeQuery = true)
    void insertarAgenda(@Param("fecha") LocalDate fecha, @Param("disponibilidad") String disponibilidad, @Param("directorio_servicio_nit") Integer directorio_servicio_nit, @Param("directorio_servicio_serv_id") Integer directorio_servicio_serv_id, @Param("directorio_medico_nit") Integer directorio_medico_nit, @Param("directorio_medico_regmed") Integer directorio_medico_regmed, @Param("servicio_medico_regmed") Integer servicio_medico_regmed, @Param("servicio_medico_serv_id") Integer servicio_medico_serv_id, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE agenda SET fecha = :fecha, disponibilidad = :disponibilidad, directorio_servicio_nit = :directorio_servicio_nit, directorio_servicio_serv_id = :directorio_servicio_serv_id, directorio_medico_nit = :directorio_medico_nit, directorio_medico_regmed = :directorio_medico_regmed, servicio_medico_regmed = :servicio_medico_regmed, servicio_medico_serv_id = :servicio_medico_serv_id WHERE idagenda = :id", nativeQuery = true)
    void actualizarAgenda(@Param("fecha") LocalDate fecha, @Param("disponibilidad") String disponibilidad, @Param("directorio_servicio_nit") Integer directorio_servicio_nit, @Param("directorio_servicio_serv_id") Integer directorio_servicio_serv_id, @Param("directorio_medico_nit") Integer directorio_medico_nit, @Param("directorio_medico_regmed") Integer directorio_medico_regmed, @Param("servicio_medico_regmed") Integer servicio_medico_regmed, @Param("servicio_medico_serv_id") Integer servicio_medico_serv_id, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM agenda WHERE idagenda = :id", nativeQuery = true)
    void eliminarAgenda(@Param("id") Integer id);

    
    // RFC5: – CONSULTA LA AGENDA DE DISPONIBILIDAD DE UN SERVICIO DE SALUD - SERIALIZABLE
    
    public interface RespuestaDisponibilidadServicio {
        String getNombreServicio();
        String getFechaDisponibilidad();
        String getNombreMedico();
    }
    @Query(value = "SELECT s.tiposervicio AS nombreServicio, " +
                   "a.fecha AS fechaDisponibilidad, " +
                   "m.nombre AS nombreMedico " +
                   "FROM agenda a " +
                   "JOIN servicios_medico sm ON a.servicio_medico_serv_id = sm.servicio_servicio_id " +
                   "JOIN medico m ON sm.medico_numregistromedico = m.numregistromedico " +
                   "JOIN servicio_salud s ON sm.servicio_servicio_id = s.idservicio " +
                   "WHERE (:idServicio IS NULL OR s.idservicio = :idServicio) " +
                   "AND (:idMedico IS NULL OR m.numregistromedico = :idMedico) " +
                   "AND a.fecha BETWEEN :startDate AND :endDate " +
                   "ORDER BY a.fecha ASC",
           nativeQuery = true)

    Collection<RespuestaDisponibilidadServicio> consultarDisponibilidadSerializable(
        @Param("idServicio") Integer idServicio,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate,
        @Param("idMedico") Integer idMedico
    );


}
