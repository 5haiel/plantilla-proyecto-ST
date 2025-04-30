package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.HistorialCitas;
import uniandes.edu.co.proyecto.modelo.HistorialCitasPK;


public interface  HistorialCitasRepository extends JpaRepository<HistorialCitas, HistorialCitasPK> {
    
    @Query(value = "SELECT * FROM HISTORIAL_CITAS", nativeQuery = true)
    Collection<HistorialCitas> darAllHistorialCitas();

    @Query(value = "SELECT * FROM HISTORIAL_CITAS WHERE ID_CITA = :id AND IPS_NIT = :nit", nativeQuery = true)
    HistorialCitas darHistorialCita(Integer id, String nit);

    @Query(value = "SELECT * FROM HISTORIAL_CITAS WHERE IPS_NIT = :nit", nativeQuery = true)
    Collection<HistorialCitas> darHistorialCitasIps(String nit);

    @Modifying
    @Transactional
    @Query(value="INSERT INTO HISTORIAL_CITAS (IDCITA, IPS_NIT) VALUES (:id_cita, :nit_ips)", nativeQuery = true)
    void insertarHistorialCita(@Param("id_cita")Integer id_cita,@Param("nit_ips") Integer nit_ips);

    @Modifying
    @Transactional
    @Query(value="UPDATE HISTORIAL_CITAS SET ID_CITA = :id_cita, IPS_NIT = :nit_ips WHERE ID_CITA = :id_cita AND IPS_NIT = :nit_ips", nativeQuery = true)
    void actualizarHistorialCita(@Param("id_cita")Integer id_cita, @Param("nit_ips") String nit_ips);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM HISTORIAL_CITAS WHERE ID_CITA = :id_cita AND IPS_NIT = :nit_ips", nativeQuery = true)
    void eliminarHistorialCita(@Param("id_cita")Integer id_cita, @Param("nit_ips") String nit_ips);

}
