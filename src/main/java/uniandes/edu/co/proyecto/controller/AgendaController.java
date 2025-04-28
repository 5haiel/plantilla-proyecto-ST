package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Agenda;
import uniandes.edu.co.proyecto.repositorio.AgendaRepository;

@RestController
public class AgendaController {
    
    @Autowired
    private AgendaRepository agendaRepository;

    public String agenda(Model model) {
        model.addAttribute("agenda", agendaRepository.darAllAgendas());
        return model.toString();
    }
    public String agendaForm(Model model) {
        model.addAttribute("agenda", new Agenda());
        return "agendaForm";
    }
    public String agendaGuardar(@RequestBody Agenda agenda) {
        // Integer ds_nit = agenda.getDirectorioServicio().getPk().getId_ips().getNit();
        // Integer ds_numRegistroMedico = agenda.getDirectorioServicio().getPk().getId_serviciosalud().getServicioSaludId();
        // Integer dm_nit = agenda.getDirectorioMedico().getPk().getId_Ips().getNit();
        // Integer dm_numRegistroMedico = agenda.getDirectorioMedico().getPk().getNum_registromedico().getNumRegistroMedico();
        // Integer sm_numRegistroMedico = agenda.getServicioSalud().getServiciosalud().getServicioSaludId();
        // agendaRepository.insertarAgenda(agenda.getFecha(), agenda.getDisponibilidad(), ds_nit, ds_numRegistroMedico, );
        return "redirect:/agenda";
    }
    public String agendaEditarForm(@PathVariable("id_agenda") Integer id_agenda, @PathVariable("id_usuario") Integer id_usuario, @PathVariable("id_medico") Integer id_medico, Model model) {
        Agenda agenda = agendaRepository.darAgendaPorId(id_agenda);
        if (agenda != null) {
            model.addAttribute("agenda", agenda);
            return "agendaEditForm";
        } else {
            return "redirect:/agenda";
        }
    }

}
