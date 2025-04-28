package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.DirectorioMedico;
import uniandes.edu.co.proyecto.repositorio.DirectorioMedicoRepository;

@RestController
public class DirectorioMedicoController {
    @Autowired
    private DirectorioMedicoRepository directorioMedicoRepository;

    @GetMapping("/directorios-medicos")
    public String directoriosMedicos(Model model) {
        model.addAttribute("directoriosMedicos", directorioMedicoRepository.darAllDirectorioMedico());
        return model.toString();
    }

    @GetMapping("/directorios-medicos/medico/{numRegistroMedico}")
    public String directoriosMedicosPorMedico(@PathVariable("numRegistroMedico") Integer numRegistroMedico, Model model) {
        model.addAttribute("directoriosMedicos",
            directorioMedicoRepository.darDirectorioMedicoPorMedico(numRegistroMedico));
        return model.toString();
    }

    @GetMapping("/directorios-medicos/ips/{nit}")
    public String directoriosMedicosPorIps(@PathVariable("nit") Integer nit, Model model) {
        model.addAttribute("directoriosMedicos", directorioMedicoRepository.darDirectorioMedicoPorIps(nit));
        return model.toString();
    }

    @GetMapping("/directorios-medicos/{numRegistroMedico}/{nit}")
    public DirectorioMedico obtenerDirectorioMedico(
        @PathVariable("numRegistroMedico") Integer numRegistroMedico,
        @PathVariable("nit") Integer nit) {
        return directorioMedicoRepository.darDirectorioMedico(numRegistroMedico, nit);
    }

    @GetMapping("/directorios-medicos/new")
    public String directorioMedicoForm(Model model) {
        model.addAttribute("directorioMedico", new DirectorioMedico());
        return "directorioMedicoForm";
    }

    @GetMapping("/directorios-medicos/{numRegistroMedico}/{nit}/edit")
    public String directorioMedicoEditarForm(@PathVariable("numRegistroMedico") Integer numRegistroMedico,
        @PathVariable("nit") Integer nit, Model model) {
        DirectorioMedico directorioMedico = directorioMedicoRepository.darDirectorioMedico(numRegistroMedico, nit);
        if (directorioMedico != null) {
            model.addAttribute("directorioMedico", directorioMedico);
            return "directorioMedicoEditForm";
        } else {
            return "redirect:/directorios-medicos";
        }
    }

    @PostMapping("/directorios-medicos/new/save")
    public String directorioMedicoGuardar(DirectorioMedico directorioMedico) {
        directorioMedicoRepository.insertarDirectorioMedico(directorioMedico.getPk().getNum_registromedico().getNumRegistroMedico(),
            directorioMedico.getPk().getId_Ips().getNit());
        return "redirect:/directorios-medicos";
    }

    @PostMapping("/directorios-medicos/{numRegistroMedico}/{nit}/edit/save")
    public String directorioMedicoEditarGuardar(@PathVariable("numRegistroMedico") Integer numRegistroMedico,
        @PathVariable("nit") Integer nit, DirectorioMedico directorioMedico) {
        directorioMedicoRepository.actualizarDirectorioMedico(directorioMedico.getPk().getNum_registromedico().getNumRegistroMedico(),
            directorioMedico.getPk().getId_Ips().getNit());
        return "redirect:/directorios-medicos";
    }

    @DeleteMapping("/directorios-medicos/{numRegistroMedico}/{nit}/delete")
    public String directorioMedicoEliminar(@PathVariable("numRegistroMedico") Integer numRegistroMedico,
        @PathVariable("nit") Integer nit) {
        directorioMedicoRepository.eliminarDirectorioMedico(numRegistroMedico, nit);
        return "redirect:/directorios-medicos";
    }
}