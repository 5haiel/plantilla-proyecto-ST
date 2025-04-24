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

import uniandes.edu.co.proyecto.modelo.ServicioSalud;
import uniandes.edu.co.proyecto.repositorio.ServicioSaludRepository;

@RestController
public class ServicioSaludController {

    @Autowired
    private ServicioSaludRepository servicioSaludRepository;

    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("ips", servicioSaludRepository.darAllServicios());
        return model.toString();
    }
    

    @GetMapping("/servicios/{id}")
    public ServicioSalud obtenerServicio(@PathVariable("id") Integer id) {
        return servicioSaludRepository.darServicio(id);
    }

    @PostMapping("/servicios/new/save")
    public String guardarServicio(@RequestBody ServicioSalud servicioSalud) {
        servicioSaludRepository.insertarServicio(
            servicioSalud.getIdServicio(),
            servicioSalud.getTipoServicio(),
            servicioSalud.getDescripcion(),
            servicioSalud.getServicioSaludId()
        );
        return "Servicio de salud registrado exitosamente.";
    }

    @PostMapping("/servicios/{id}/edit/save")
    public String editarServicio(@PathVariable("id") Long id, @ModelAttribute ServicioSalud servicioSalud) {
        servicioSaludRepository.actualizarServicio(
            servicioSalud.getIdServicio(),
            servicioSalud.getTipoServicio(),
            servicioSalud.getDescripcion(),
            servicioSalud.getServicioSaludId()
        );
        return "Servicio de salud actualizado exitosamente.";
    }

    @DeleteMapping("/servicios/{id}/delete")
    public String eliminarServicio(@PathVariable("id") Integer id) {
        servicioSaludRepository.eliminarServicio(id);
        return "Servicio de salud eliminado exitosamente.";
    }
}