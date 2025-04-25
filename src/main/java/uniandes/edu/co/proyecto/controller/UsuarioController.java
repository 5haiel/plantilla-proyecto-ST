package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.darTodosLosUsuarios());
        return model.toString();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario obtenerUsuario(@PathVariable("id") Integer id) {
        return usuarioRepository.darUsuario(id);
    }

    @PostMapping("/usuarios/new/save")
    public String guardarUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.insertarUsuario(
            usuario.getTipodocumento(),
            usuario.getNumdocumento(),
            usuario.getNombre(),
            usuario.getFechanacimiento(),
            usuario.getTelefono(),
            usuario.getTipocontribucion(),
            usuario.getUsuarioid()
        );
        return "Usuario registrado exitosamente.";
    }

    @PostMapping("/usuarios/{id}/edit/save")
    public String editarUsuario(@PathVariable("id") Integer id, @ModelAttribute Usuario usuario) {
        usuarioRepository.actualizarUsuario(
            id,
            usuario.getTipodocumento(),
            usuario.getNumdocumento(),
            usuario.getNombre(),
            usuario.getFechanacimiento(),
            usuario.getTelefono(),
            usuario.getTipocontribucion()
        );
        return "Usuario actualizado exitosamente.";
    }

    @DeleteMapping("/usuarios/{id}/delete")
    public String eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioRepository.eliminarUsuario(id);
        return "Usuario eliminado exitosamente.";
    }
}