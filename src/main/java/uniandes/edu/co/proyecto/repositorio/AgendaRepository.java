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

    @Query(value = "SELECT * FROM agenda WHERE id = :id", nativeQuery = true)
    Agenda darAgendaPorId(Integer id);

    @Query(value = "SELECT * FROM agenda WHERE id = :id AND fecha = :fecha", nativeQuery = true)
    Agenda darAgendaPorIdYFecha(@Param("id") Integer id, @Param("fecha") LocalDate fecha);

    // RF9: Agendar un servicio de salud por parte de un afiliado
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO agenda (fecha, disponibilidad, directorio_servicio_nit, directorio_servicio_serv_id, directorio_medico_nit, directorio_medico_regmed, servicio_medico_regmed, servicio_medico_serv_id, id) VALUES (:fecha, :disponibilidad, :directorio_servicio_nit, :directorio_servicio_serv_id, :directorio_medico_nit, :directorio_medico_regmed, :servicio_medico_regmed, :servicio_medico_serv_id, :id)", nativeQuery = true)
    void insertarAgenda(@Param("fecha") LocalDate fecha, @Param("disponibilidad") String disponibilidad, @Param("directorio_servicio_nit") Integer directorio_servicio_nit, @Param("directorio_servicio_serv_id") Integer directorio_servicio_serv_id, @Param("directorio_medico_nit") Integer directorio_medico_nit, @Param("directorio_medico_regmed") Integer directorio_medico_regmed, @Param("servicio_medico_regmed") Integer servicio_medico_regmed, @Param("servicio_medico_serv_id") Integer servicio_medico_serv_id, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE agenda SET fecha = :fecha, disponibilidad = :disponibilidad, directorio_servicio_nit = :directorio_servicio_nit, directorio_servicio_serv_id = :directorio_servicio_serv_id, directorio_medico_nit = :directorio_medico_nit, directorio_medico_regmed = :directorio_medico_regmed, servicio_medico_regmed = :servicio_medico_regmed, servicio_medico_serv_id = :servicio_medico_serv_id WHERE id = :id", nativeQuery = true)
    void actualizarAgenda(@Param("fecha") LocalDate fecha, @Param("disponibilidad") String disponibilidad, @Param("directorio_servicio_nit") Integer directorio_servicio_nit, @Param("directorio_servicio_serv_id") Integer directorio_servicio_serv_id, @Param("directorio_medico_nit") Integer directorio_medico_nit, @Param("directorio_medico_regmed") Integer directorio_medico_regmed, @Param("servicio_medico_regmed") Integer servicio_medico_regmed, @Param("servicio_medico_serv_id") Integer servicio_medico_serv_id, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM agenda WHERE id = :id", nativeQuery = true)
    void eliminarAgenda(@Param("id") Integer id);
}
