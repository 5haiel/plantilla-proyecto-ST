package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Cita;
import uniandes.edu.co.proyecto.repositorio.CitaRepository;

@RestController
public class CitaController {
    
    @Autowired
    private CitaRepository citaRepository;

    @GetMapping("/citas")
    public String citas(Model model) {
        model.addAttribute("citas", citaRepository.darAllCitas());
        return model.toString();
    }

    @GetMapping("/citas/new")
    public String citaForm(Model model) {
        model.addAttribute("cita", new Cita());
        return "citaForm";
    }
    
    @PostMapping("/citas/new/save")
    public String citaGuardar(Cita cita) {
        citaRepository.insertarCita(cita.getPk().getIdcita(), cita.getPk().getServicioordenid().getPk().getIdservicioorden(), cita.getPk().getUsuarioid().getUsuarioid(), cita.getEstado(), cita.getFecha(), cita.getDisponibilidad());
        return "redirect:/citas";
    }

    @GetMapping("/citas/{id_cita}/{id_servicioorden}/{id_usuario}/edit")
    public String citaEditarForm(Integer id_cita, Integer id_servicioorden, Integer id_usuario, Model model) {
        Cita cita = citaRepository.darCita(id_cita, id_servicioorden, id_usuario);
        if (cita != null) {
            model.addAttribute("cita", cita);
            return "citaEditForm";
        } else {
            return "redirect:/citas";
        }
    }

    @PostMapping("/citas/{id_cita}/{id_servicioorden}/{id_usuario}/edit/save")
    public String citaEditarGuardar(Integer id_cita, Integer id_servicioorden, Integer id_usuario, Cita cita) {
        citaRepository.actualizarCita(cita.getPk().getIdcita(), cita.getPk().getServicioordenid().getPk().getIdservicioorden(), cita.getPk().getUsuarioid().getUsuarioid(), cita.getEstado(), cita.getFecha(), cita.getDisponibilidad());
        return "redirect:/citas";
    }

    @DeleteMapping("/citas/{id_cita}/{id_servicioorden}/{id_usuario}/delete")
    public String citaEliminar(Integer id_cita, Integer id_servicioorden, Integer id_usuario) {
        citaRepository.eliminarCita(id_cita, id_servicioorden, id_usuario);
        return "redirect:/citas";
    }    

}
