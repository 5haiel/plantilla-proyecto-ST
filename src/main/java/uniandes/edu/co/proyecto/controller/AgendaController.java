package uniandes.edu.co.proyecto.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Agenda;
import uniandes.edu.co.proyecto.repositorio.AgendaRepository;

@RestController
public class AgendaController {
    
    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping("/agenda")
    public String agenda(Model model) {
        model.addAttribute("agenda", agendaRepository.darAllAgendas());
        return model.toString();
    }

    @GetMapping("/agenda/new")
    public String agendaForm(Model model) {
        model.addAttribute("agenda", new Agenda());
        return "agendaForm";
    }

    @PostMapping("/agenda/new/save")
    public String agendaGuardar(@RequestBody Agenda agenda) {
        Integer ds_nit = agenda.getDirectorioServicio().getPk().getId_ips().getNit();
        System.out.println("ds_nit: " + ds_nit);

        Integer ds_servid = agenda.getDirectorioServicio().getPk().getId_serviciosalud().getServicioSaludId();
        System.out.println("ds_servid: " + ds_servid);

        Integer dm_nit = agenda.getDirectorioMedico().getPk().getId_Ips().getNit();
        System.out.println("dm_nit: " + dm_nit);

        Integer dm_numRegistroMedico = agenda.getDirectorioMedico().getPk().getNum_registromedico().getNumRegistroMedico();
        System.out.println("dm_numRegistroMedico: " + dm_numRegistroMedico);

        Integer sm_numRegistroMedico = agenda.getServicioOrden().getOrden().getPk().getMedicoid().getNumRegistroMedico();
        System.out.println("sm_numRegistroMedico: " + sm_numRegistroMedico);

        Integer sm_servid = agenda.getServicioOrden().getServiciosalud().getIdServicio();
        System.out.println("sm_servid: " + sm_servid);
        
        agendaRepository.insertarAgenda(agenda.getFecha(), agenda.getDisponibilidad(), ds_nit, ds_servid, dm_nit, dm_numRegistroMedico, sm_numRegistroMedico, sm_servid, 16);
        return "redirect:/agenda";
    }

    @GetMapping("/agenda/{id_medico}/{id_usuario}/{id_agenda}/edit")
    public String agendaEditarForm(@PathVariable("id_agenda") Integer id_agenda, @PathVariable("id_usuario") Integer id_usuario, @PathVariable("id_medico") Integer id_medico, Model model) {
        Agenda agenda = agendaRepository.darAgendaPorId(id_agenda);
        if (agenda != null) {
            model.addAttribute("agenda", agenda);
            return "agendaEditForm";
        } else {
            return "redirect:/agenda";
        }
    }

    @PostMapping("/agenda/{id}/edit/save")
    public String agendaEditar(@PathVariable("id_agenda") Integer id_agenda, @PathVariable("id_usuario") Integer id_usuario, @PathVariable("id_medico") Integer id_medico, @RequestBody Agenda agenda) {
        agendaRepository.actualizarAgenda(agenda.getFecha(), agenda.getDisponibilidad(), agenda.getDirectorioServicio().getPk().getId_ips().getNit(), agenda.getDirectorioServicio().getPk().getId_serviciosalud().getServicioSaludId(), agenda.getDirectorioMedico().getPk().getId_Ips().getNit(), agenda.getDirectorioMedico().getPk().getNum_registromedico().getNumRegistroMedico(), agenda.getServicioOrden().getServiciosalud().getServicioSaludId(), agenda.getServicioOrden().getServiciosalud().getIdServicio(), id_agenda);
        return "redirect:/agenda";
    }

    @DeleteMapping("/agenda/{id_agenda}/delete")
    public String agendaEliminar(@PathVariable("id_agenda") Integer id_agenda, Model model) {
        agendaRepository.eliminarAgenda(id_agenda);
        return "redirect:/agenda";
    }

    @GetMapping("/agenda/disponibilidad/{id_servicio}")
    public String consultarDisponibilidad(@PathVariable("id_servicio") Integer idServicio, Model model) {
        LocalDate today = LocalDate.now();
        LocalDate fourWeeksLater = today.plusWeeks(4);
        agendaRepository.darAgendasPorRangoDeFechasYServicio(idServicio, today, fourWeeksLater);
        return model.toString();
    }
}
