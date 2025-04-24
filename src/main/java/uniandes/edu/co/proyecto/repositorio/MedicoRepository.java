package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer>{

    @Query(value = "SELECT * FROM medico", nativeQuery = true)
    Collection<Medico> darTodosLosMedicos();

    @Query(value = "SELECT * FROM medico WHERE numregistromedico = :id", nativeQuery = true)
    Medico darMedico(@Param("id") Integer id);

    // RF4: Registrar Médico
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO medico (numregistromedico, numerodocumento, tipodocumento, especialidad, nombre) VALUES (:numRegistroMedico, :numeroDocumento, :tipoDocumento, :especialidad, :nombre)", nativeQuery = true)
    void insertarMedico(@Param("numRegistroMedico") Integer numRegistroMedico, @Param("numeroDocumento") Integer numeroDocumento, @Param("tipoDocumento") String tipoDocumento, @Param("especialidad") String especialidad, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "UPDATE medico SET numerodocumento = :numeroDocumento, tipodocumento = :tipoDocumento, especialidad = :especialidad, nombre = :nombre WHERE numregistromedico = :numRegistroMedico", nativeQuery = true)
    void actualizarMedico(@Param("numRegistroMedico") Integer numRegistroMedico, @Param("numeroDocumento") Integer numeroDocumento, @Param("tipoDocumento") String tipoDocumento, @Param("especialidad") String especialidad, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM medico WHERE numregistromedico = :id", nativeQuery = true)
    void eliminarMedico(@Param("id") Integer id);
}