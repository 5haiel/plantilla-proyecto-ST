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

import uniandes.edu.co.proyecto.modelo.Medico;
import uniandes.edu.co.proyecto.repositorio.MedicoRepository;

@RestController
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/medicos")
    public String medicos(Model model) {
        model.addAttribute("medicos", medicoRepository.darTodosLosMedicos());
        return model.toString();
    }

    @GetMapping("/medicos/{id}")
    public Medico obtenerMedico(@PathVariable("id") Integer id) {
        return medicoRepository.darMedico(id);
    }

    @PostMapping("/medicos/new/save")
    public String guardarMedico(@RequestBody Medico medico) {
        medicoRepository.insertarMedico(
            medico.getNumRegistroMedico(),
            medico.getNumeroDocumento(),
            medico.getTipoDocumento(),
            medico.getEspecialidad(),
            medico.getNombre()
        );
        return "Médico registrado exitosamente.";
    }

    @PostMapping("/medicos/{id}/edit/save")
    public String editarMedico(@PathVariable("id") Integer id, @ModelAttribute Medico medico) {
        medicoRepository.actualizarMedico(
            id,
            medico.getNumeroDocumento(),
            medico.getTipoDocumento(),
            medico.getEspecialidad(),
            medico.getNombre()
        );
        return "Médico actualizado exitosamente.";
    }

    @DeleteMapping("/medicos/{id}/delete")
    public String eliminarMedico(@PathVariable("id") Integer id) {
        medicoRepository.eliminarMedico(id);
        return "Médico eliminado exitosamente.";
    }
}
