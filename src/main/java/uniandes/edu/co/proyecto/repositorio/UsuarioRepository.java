package uniandes.edu.co.proyecto.repositorio;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value="SELECT * FROM usuario" ,nativeQuery = true)
    Collection<Usuario> darTodosLosUsuarios();

    @Query(value="SELECT * FROM usuario WHERE usuario_id = :id" ,nativeQuery = true)
    Usuario darUsuario(@Param("id") Integer id);

    // RF5: Registrar Usuario
    @Modifying
    @Transactional
    @Query(value="INSERT INTO usuario (tipodocumento, numdocumento, nombre, fechanacimiento, telefono, tipocontribucion, usuario_id) VALUES (:tipodocumento, :numdocumento, :nombre, :fechanacimiento, :telefono, :tipocontribucion, :usuarioid)", nativeQuery = true)
    void insertarUsuario(@Param("tipodocumento") String tipodocumento, @Param("numdocumento") Integer numdocumento, @Param("nombre") String nombre, @Param("fechanacimiento") LocalDate fechanacimiento, @Param("telefono") Integer telefono, @Param("tipocontribucion") String tipocontribucion, @Param("usuarioid") Integer usuarioid);

    @Modifying
    @Transactional
    @Query(value="UPDATE usuario SET tipodocumento = :tipodocumento, numdocumento = :numdocumento, nombre = :nombre, fechanacimiento = :fechanacimiento, telefono = :telefono, tipocontribucion = :tipocontribucion WHERE usuarioid = :id", nativeQuery = true)
    void actualizarUsuario(@Param("id") Integer id, @Param("tipodocumento") String tipodocumento, @Param("numdocumento") Integer numdocumento, @Param("nombre") String nombre, @Param("fechanacimiento") LocalDate fechanacimiento, @Param("telefono") Integer telefono, @Param("tipocontribucion") String tipocontribucion);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM usuario WHERE usuario_id = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") Integer id);
}
