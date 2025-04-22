package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Ips;

public interface IpsRepository extends JpaRepository<Ips, Integer> {
   
    @Query(value = "SELECT * FROM ips", nativeQuery = true)
    Collection<Ips> darAllIps();  
    
    @Query(value = "SELECT * FROM ips WHERE NIT = :id", nativeQuery = true)
    Ips darIps(@Param("id") Integer id);

    // RF1: Registrar IPS
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ips (NIT, nombre, tipo, direccion, telefono) VALUES (:nit, :nombre, :tipo, :direccion, :telefono)", nativeQuery = true)
    void insertarIps(@Param("nit") Integer nit, @Param("nombre") String nombre, @Param("tipo") String tipo, @Param("direccion") String direccion, @Param("telefono") Integer telefono);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ips SET nombre = :nombre, tipo = :tipo, direccion = :direccion, telefono = :telefono WHERE NIT = :nit", nativeQuery = true)
    void actualizarIps(@Param("nit") Integer nit, @Param("nombre") String nombre, @Param("tipo") String tipo, @Param("direccion") String direccion, @Param("telefono") Integer telefono);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ips WHERE NIT = :id", nativeQuery = true)
    void eliminarIps(@Param("id") Integer id);
}
