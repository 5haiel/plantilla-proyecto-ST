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

import uniandes.edu.co.proyecto.modelo.ServicioOrden;
import uniandes.edu.co.proyecto.repositorio.ServicioOrdenRepository;

@RestController
public class ServicioOrdenController {
    
    @Autowired
    private ServicioOrdenRepository servicioOrdenRepository;

    @GetMapping("/serviciosorden")
    public String serviciosOrden(Model model){
        model.addAttribute("serviciosorden", servicioOrdenRepository.darAllServicioOrdenes());
        return model.toString();
    }

    @GetMapping("/serviciosorden/new")
    public String serviciosOrdenForm(Model model) {
        model.addAttribute("serviciosorden", new ServicioOrden());
        return "serviciosordenForm";
    }

    @PostMapping("/serviciosorden/new/save")
    public String serviciosOrdenGuardar(@RequestBody ServicioOrden servicioOrden) {
        servicioOrdenRepository.insertarServicioOrden(servicioOrden.getPk().getIdservicioorden(), servicioOrden.getOrden().getPk().getIdorden(), servicioOrden.getOrden().getPk().getMedicoid().getNumRegistroMedico(), servicioOrden.getServiciosalud().getServicioSaludId());
        return "redirect:/serviciosorden";
    }

    @GetMapping("/serviciosorden/{id}/edit")
    public String serviciosOrdenEditarForm(@PathVariable("id") Integer id, Model model) {
        ServicioOrden servicioOrden = servicioOrdenRepository.darServicioOrdenPorId(id);
        if (servicioOrden != null) {
            model.addAttribute("serviciosorden", servicioOrden);
            return "serviciosordenEditForm";
        } else {
            return "redirect:/serviciosorden";
        }
    }

    @PostMapping("/serviciosorden/{id}/edit/save")
    public String serviciosOrdenEditarGuardar(@PathVariable("id") Integer id, @ModelAttribute ServicioOrden servicioOrden) {
        servicioOrdenRepository.actualizarServicioOrden(servicioOrden.getPk().getIdservicioorden(), servicioOrden.getOrden().getPk().getIdorden(), servicioOrden.getOrden().getPk().getMedicoid().getNumRegistroMedico(), servicioOrden.getServiciosalud().getServicioSaludId());
        return "redirect:/serviciosorden";
    }

    @DeleteMapping("/serviciosorden/{id}/delete")
    public String serviciosOrdenEliminar(@PathVariable("id") Integer id) {
        servicioOrdenRepository.eliminarServicioOrden(id);
        return "redirect:/serviciosorden";
    }
}
