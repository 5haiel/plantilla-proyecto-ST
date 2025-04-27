package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.DirectorioServicio;
import uniandes.edu.co.proyecto.modelo.DirectorioServicioPK;
import uniandes.edu.co.proyecto.repositorio.DirectorioServicioRepository;

@RestController
public class DirectorioServicioController {
    @Autowired
    DirectorioServicioRepository directorioServicioRepository;

    @GetMapping("/directorio_servicio")
    public String darDirectorioServicio(Model model) {
        model.addAttribute("directorio_servicio", directorioServicioRepository.darAllDirectorioServicio());
        return model.toString();
    }

    @PostMapping("/directorio_servicio/new/save")
    public String guardarDirectorioServicio(@RequestBody DirectorioServicio directorioservicio) {
        DirectorioServicioPK pk = directorioservicio.getPk();
        Integer ips_nit = pk.getId_ips().getNit();
        Integer serviciosaludid = pk.getId_serviciosalud().getIdServicio();
        directorioServicioRepository.insertarDirectorioServicio(ips_nit, serviciosaludid);
        return "redirect:/directorio_servicio";
    }

    @PostMapping("/directorio_servicio/{id_ips}/{id_serviciosalud}/edit/save")
    public String editarDirectorioServicio(@Param("id_ips") Integer id_ips,@Param("id_serviciosalud") Integer id_serviciosalud) {
        directorioServicioRepository.actualizarDirectorioServicio(id_ips, id_serviciosalud);
        return "redirect:/directorio_servicio";
    }

    @DeleteMapping("/directorio_servicio/{id_ips}/{id_serviciosalud}/delete")
    public String eliminarDirectorioServicio(@Param("id_ips") Integer id_ips,@Param("id_serviciosalud") Integer id_serviciosalud) {
        directorioServicioRepository.eliminarDirectorioServicio(id_ips, id_serviciosalud);
        return "redirect:/directorio_servicio";
    }

    @GetMapping("/directorio_servicio/{id_ips}")
    public String darDirectorioServicioPorIps(@Param("id_ips") Integer id_ips, Model model) {
        model.addAttribute("directorio_servicio", directorioServicioRepository.darDirectorioServicioPorIps(id_ips));
        return model.toString();
    }

    @GetMapping("/directorio_servicio/servicio/{id_serviciosalud}")
    public String darDirectorioServicioPorServicio(@Param("id_serviciosalud")Integer id_serviciosalud, Model model) {
        model.addAttribute("directorio_servicio", directorioServicioRepository.darDirectorioServicioPorServicio(id_serviciosalud));
        return model.toString();
    }

    @GetMapping("/directorio_servicio/{id_ips}/{id_serviciosalud}")
    public String darDirectorioServicioEspecifico(@Param("id_ips") Integer id_ips,@Param("id_serviciosalud") Integer id_serviciosalud, Model model) {
        model.addAttribute("directorio_servicio", directorioServicioRepository.darDirectorioServicio(id_ips, id_serviciosalud));
        return model.toString();
    }
}
