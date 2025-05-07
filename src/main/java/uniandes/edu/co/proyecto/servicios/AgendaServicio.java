package uniandes.edu.co.proyecto.servicios;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Agenda;
import uniandes.edu.co.proyecto.modelo.Orden;
import uniandes.edu.co.proyecto.repositorio.AgendaRepository;
import uniandes.edu.co.proyecto.repositorio.OrdenRepository;

@Service
public class AgendaServicio {

    private AgendaRepository agendaRepository;

    private OrdenRepository ordenRepository;

    public AgendaServicio(AgendaRepository agendaRepository, OrdenRepository ordenRepository) {
        this.agendaRepository = agendaRepository;
        this.ordenRepository = ordenRepository;
    }
    
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void agendarServicio(Integer idAgenda, Integer idOrden, Integer idMedico, Integer idUsuario) throws Exception {
        // Step 1: Confirm availability with locking
        Agenda agenda = agendaRepository.darAgendaPorIdCandado(idAgenda);
        if (agenda == null || !agenda.getDisponibilidad().equalsIgnoreCase("Disponible")) {
            throw new Exception("El servicio no está disponible.");
        }

        // Step 2: Validate the service order
        Orden orden = ordenRepository.darOrden(idOrden, idMedico, idUsuario);
        if (orden == null) {
            throw new Exception("La orden de servicio no existe.");
        }

        // Step 3: Schedule the service
        agendaRepository.actualizarAgenda(
            agenda.getFecha(),
            "Reservado",
            agenda.getDirectorioServicio().getPk().getId_ips().getNit(),
            agenda.getDirectorioServicio().getPk().getId_serviciosalud().getIdServicio(),
            agenda.getDirectorioMedico().getPk().getId_Ips().getNit(),
            agenda.getDirectorioMedico().getPk().getNum_registromedico().getNumRegistroMedico(),
            agenda.getServiciosMedico().getPk().getId_medico().getNumRegistroMedico(),
            agenda.getServiciosMedico().getPk().getId_serviciosalud().getServicioSaludId(),
            idAgenda
        );
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, readOnly = true)
    public Collection<Agenda> consultarDisponibilidadSerializable(Integer idServicio, LocalDate startDate, LocalDate endDate, Integer idMedico) throws InterruptedException {
        try {
            Thread.sleep(30000); // Simulate a 30-second delay
            return agendaRepository.darAgendasPorRangoDeFechasYServicio(idServicio, startDate, endDate);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la disponibilidad: " + e.getMessage());
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class, readOnly = true)
    public Collection<Agenda> consultarDisponibilidadReadCommitted(Integer idServicio, LocalDate startDate, LocalDate endDate, Integer idMedico) throws InterruptedException {
        try {
            Thread.sleep(30000); // Simulate a 30-second delay
            return agendaRepository.darAgendasPorRangoDeFechasYServicio(idServicio, startDate, endDate);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la disponibilidad: " + e.getMessage());
        }
    }
}
