package uniandes.edu.co.proyecto.servicios;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Agenda;
import uniandes.edu.co.proyecto.modelo.Orden;
import uniandes.edu.co.proyecto.repositorio.AgendaRepository;
import uniandes.edu.co.proyecto.repositorio.AgendaRepository.RespuestaDisponibilidadServicio;
import uniandes.edu.co.proyecto.repositorio.OrdenRepository;
import uniandes.edu.co.proyecto.repositorio.ServicioOrdenRepository;

@Service
public class AgendaServicio {

    private AgendaRepository agendaRepository;

    private OrdenRepository ordenRepository;

    private ServicioOrdenRepository servicioOrdenRepository;

    public AgendaServicio(AgendaRepository agendaRepository, OrdenRepository ordenRepository) {
        this.agendaRepository = agendaRepository;
        this.ordenRepository = ordenRepository;

    }
    
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public void agendarServicio(Integer idAgenda, Integer idOrden, Integer idMedico, Integer idUsuario) throws Exception {
        // Confirma disponibilidad con candado
        Agenda agenda = agendaRepository.darAgendaPorIdCandado(idAgenda);
        if (agenda == null || !agenda.getDisponibilidad().equalsIgnoreCase("Disponible")) {
            throw new Exception("El servicio no está disponible.");
        }

        // Valida la orden
        Orden orden = ordenRepository.darOrden(idOrden, idMedico, idUsuario);
        System.out.println(idOrden);
        System.out.println(idMedico);
        System.out.println(idUsuario);
        if (orden == null) {
            throw new Exception("La orden de servicio no existe o no está asociada al médico y usuario indicados.");
        }

        // Valida la concordancia de datos
        if (orden.getPk().getMedicoid().getNumRegistroMedico() != agenda.getServiciosMedico().getPk().getId_medico().getNumRegistroMedico()) {
            throw new Exception("El médico de la orden no coincide con el médico indicado.");
        }

        if (idUsuario != null) {
            if (orden.getPk().getUsuarioid().getUsuarioid() != idUsuario) {
                throw new Exception("El usuario de la orden no coincide con el usuario indicado.");
            }
        }



        // Agenda el servicio
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
    public Collection<RespuestaDisponibilidadServicio> consultarDisponibilidadSerializable(Integer idServicio, LocalDate startDate, LocalDate endDate, Integer idMedico) throws InterruptedException {
        try {
            Thread.sleep(30000); // Simulate a 30-second delay
            return agendaRepository.consultarDisponibilidadSerializable(idServicio, startDate, endDate, idMedico);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la disponibilidad: " + e.getMessage());
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class, readOnly = true)
    public Collection<RespuestaDisponibilidadServicio> consultarDisponibilidadReadCommitted(Integer idServicio, LocalDate startDate, LocalDate endDate, Integer idMedico) throws InterruptedException {
        try {
            Thread.sleep(30000); // Simulate a 30-second delay
            return agendaRepository.consultarDisponibilidadSerializable(idServicio, startDate, endDate, idMedico);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la disponibilidad: " + e.getMessage());
        }
    }
}
