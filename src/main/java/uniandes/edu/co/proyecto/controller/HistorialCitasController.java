package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.HistorialCitas;
import uniandes.edu.co.proyecto.repositorio.HistorialCitasRepository;


@RestController
public class HistorialCitasController {

    @Autowired
    private HistorialCitasRepository historialCitaRepository;

    @GetMapping("/historialCitas")
    public String historialCitas() {
        return historialCitaRepository.darAllHistorialCitas().toString();
    }
    @GetMapping("/historialCitas/{id}/{nit}")
    public String historialCita(@PathVariable Integer id, @PathVariable String nit) {
        return historialCitaRepository.darHistorialCita(id, nit).toString();
    }

    @PostMapping("/historialCitas/new/")
    public String historialCitaForm(@RequestBody HistorialCitas historialCita) {
        historialCitaRepository.insertarHistorialCita(historialCita.getCitaId().getPk().getIdcita(), historialCita.getIpsId().getNit());
        return "historialCitaNueva";
    }

}