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

import uniandes.edu.co.proyecto.modelo.Orden;
import uniandes.edu.co.proyecto.repositorio.OrdenRepository;


@RestController
public class OrdenController {
    
    @Autowired
    private OrdenRepository ordenRepository;

    @GetMapping("/orden")
    public String orden(Model model) {
        model.addAttribute("orden", ordenRepository.darAllOrdenes());
        return model.toString();
    }
    
    @GetMapping("/orden/new")
    public String ordenForm(Model model) {
        model.addAttribute("orden", new Orden());
        return "ordenForm";
    }

    @PostMapping("/orden/new/save")
    public String ordenGuardar(@RequestBody Orden orden) {
        ordenRepository.insertarOrden(orden.getPk().getIdorden(), orden.getPk().getMedicoid().getNumeroDocumento(), orden.getPk().getUsuarioid().getUsuarioid(), orden.getFechaemision(), orden.getEstado());
        return "redirect:/orden";
    }

    @GetMapping("/orden/{id_medico}/{id_usuario}/{id_orden}/edit")
    public String ordenEditarForm(@PathVariable("id_orden") Integer id_orden, @PathVariable("id_usuario") Integer id_usuario, @PathVariable("id_medico") Integer id_medico, Model model) {
        Orden orden = ordenRepository.darOrden(id_orden, id_medico, id_usuario);
        if (orden != null) {
            model.addAttribute("orden", orden);
            return "ordenEditForm";
        } else {
            return "redirect:/orden";
        }
    }

    @PostMapping("/orden/{id}/edit/save")
    public String ordenEditarGuardar(@PathVariable("id_orden") Integer id_orden, @PathVariable("id_usuario") Integer id_usuario, @PathVariable("id_medico") Integer id_medico, @ModelAttribute Orden orden) {
        ordenRepository.actualizarOrden(id_orden, orden.getPk().getMedicoid().getNumRegistroMedico(), orden.getPk().getUsuarioid().getNumdocumento(), orden.getFechaemision(), orden.getEstado());
        return "redirect:/orden";
    }

    @DeleteMapping("/orden/{id}/delete")
    public String ordenEliminar(@PathVariable("id_orden") Integer id_orden, @PathVariable("id_usuario") Integer id_usuario, @PathVariable("id_medico") Integer id_medico, Model model) {
        ordenRepository.eliminarOrden(id_orden, id_medico, id_usuario);
        return "redirect:/orden";
    }
}
