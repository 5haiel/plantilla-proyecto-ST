package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.DirectorioMedico;
import uniandes.edu.co.proyecto.modelo.DirectorioMedicoPK;

public interface DirectorioMedicoRepository extends JpaRepository<DirectorioMedico, DirectorioMedicoPK> {

    @Query(value = "SELECT * FROM directorio_medico", nativeQuery = true)
    Collection<DirectorioMedico> darAllDirectorioMedico();
    
    @Query(value = "SELECT * FROM directorio_medico WHERE medico_numregistromedico = :numregistromedico AND ips_nit = :nit", nativeQuery = true)
    DirectorioMedico darDirectorioMedico(@Param("numregistromedico") Integer numregistromedico, @Param("nit") Integer nit);

    @Query(value = "SELECT * FROM directorio_medico WHERE ips_nit = :nit", nativeQuery = true)
    Collection<DirectorioMedico> darDirectorioMedicoPorIps(@Param("nit") Integer nit);

    @Query(value = "SELECT * FROM directorio_medico WHERE medico_numregistromedico = :numregistromedico", nativeQuery = true)
    Collection<DirectorioMedico> darDirectorioMedicoPorMedico(@Param("numregistromedico") Integer numregistromedico);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO directorio_medico (medico_numregistromedico, ips_nit) VALUES (:numregistromedico, :nit)", nativeQuery = true)
    void insertarDirectorioMedico(@Param("numregistromedico") Integer numregistromedico, @Param("nit") Integer nit);

    @Modifying
    @Transactional
    @Query(value = "UPDATE directorio_medico SET ips_nit = :nit WHERE medico_numregistromedico = :numregistromedico", nativeQuery = true)
    void actualizarDirectorioMedico(@Param("numregistromedico") Integer numregistromedico, @Param("nit") Integer nit);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM directorio_medico WHERE medico_numregistromedico = :numregistromedico AND ips_nit = :nit", nativeQuery = true)
    void eliminarDirectorioMedico(@Param("numregistromedico") Integer numregistromedico, @Param("nit") Integer nit);
}
