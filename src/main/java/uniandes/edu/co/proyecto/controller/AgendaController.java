package uniandes.edu.co.proyecto.controller;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Agenda;
import uniandes.edu.co.proyecto.repositorio.AgendaRepository;
import uniandes.edu.co.proyecto.repositorio.AgendaRepository.RespuestaDisponibilidadServicio;
import uniandes.edu.co.proyecto.servicios.AgendaServicio;

@RestController
public class AgendaController {
    
    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private AgendaServicio agendaServicio;

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
        Integer ds_servid = agenda.getDirectorioServicio().getPk().getId_serviciosalud().getServicioSaludId();
        Integer dm_nit = agenda.getDirectorioMedico().getPk().getId_Ips().getNit();
        Integer dm_numRegistroMedico = agenda.getDirectorioMedico().getPk().getNum_registromedico().getNumRegistroMedico();
        Integer sm_numRegistroMedico = agenda.getServiciosMedico().getPk().getId_medico().getNumRegistroMedico();
        Integer sm_servid = agenda.getServiciosMedico().getPk().getId_serviciosalud().getServicioSaludId();
    
        agendaRepository.insertarAgenda(agenda.getFecha(), agenda.getDisponibilidad(), ds_nit, ds_servid, dm_nit, dm_numRegistroMedico, sm_numRegistroMedico, sm_servid, agenda.getIdagenda());
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

    @PostMapping("/agenda/{id_agenda}/edit/save")
    public String agendaEditar(@PathVariable("id_agenda") Integer id_agenda, @RequestBody Agenda agenda) {
        agendaRepository.actualizarAgenda(agenda.getFecha(), agenda.getDisponibilidad(), agenda.getDirectorioServicio().getPk().getId_ips().getNit(), agenda.getDirectorioServicio().getPk().getId_serviciosalud().getServicioSaludId(), agenda.getDirectorioMedico().getPk().getId_Ips().getNit(), agenda.getDirectorioMedico().getPk().getNum_registromedico().getNumRegistroMedico(), agenda.getServiciosMedico().getPk().getId_medico().getNumRegistroMedico(), agenda.getServiciosMedico().getPk().getId_serviciosalud().getServicioSaludId(), id_agenda);
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
        //agendaRepository.darAgendasPorRangoDeFechasYServicio(idServicio, today, fourWeeksLater);
        model.addAttribute("agenda",agendaRepository.darAgendasPorRangoDeFechasYServicio(idServicio, today, fourWeeksLater));
        return model.toString();
    }
    
    @PostMapping("/agenda/agendar")
    public String agendarServicio(
        @RequestParam Integer idAgenda, 
        @RequestParam Integer idOrden, 
        @RequestParam(required = false) Integer idMedico, 
        @RequestParam(required = false) Integer idUsuario) {
        try {
            agendaServicio.agendarServicio(idAgenda, idOrden, idMedico, idUsuario);
            return "Servicio agendado exitosamente.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    @GetMapping("/agenda/disponibilidad/serializable")
    public Collection<RespuestaDisponibilidadServicio> consultarDisponibilidadSerializable(
        @RequestParam(required = false) Integer idServicio,
        @RequestParam LocalDate startDate,
        @RequestParam LocalDate endDate,
        @RequestParam(required = false) Integer idMedico
    ) throws InterruptedException {  
        try {
            Collection<RespuestaDisponibilidadServicio> disponibilidad = agendaServicio.consultarDisponibilidadSerializable(idServicio, startDate, endDate, idMedico);
            return disponibilidad;
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error: " + e.getMessage());
        }
    }
    
    @GetMapping("/agenda/disponibilidad/read-committed")
    public Collection<RespuestaDisponibilidadServicio> consultarDisponibilidadReadCommitted(
        @RequestParam(required = false) Integer idServicio,
        @RequestParam LocalDate startDate,
        @RequestParam LocalDate endDate,
        @RequestParam(required = false) Integer idMedico
    ) throws InterruptedException {
        try {
            Collection<RespuestaDisponibilidadServicio> disponibilidad = agendaServicio.consultarDisponibilidadReadCommitted(idServicio, startDate, endDate, idMedico);
            return disponibilidad;
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error: " + e.getMessage());
        }
    }
    
}
