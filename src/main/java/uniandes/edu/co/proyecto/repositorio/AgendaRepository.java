package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniandes.edu.co.proyecto.modelo.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    @Query(value = "SELECT * FROM agenda", nativeQuery = true)
    Collection<Agenda> darAllAgendas();

    @Query(value = "SELECT * FROM agenda WHERE id = :id", nativeQuery = true)
    Agenda darAgendaPorId(Integer id);

    
}
