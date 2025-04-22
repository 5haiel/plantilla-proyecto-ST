package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import uniandes.edu.co.proyecto.modelo.Ips;
import uniandes.edu.co.proyecto.repositorio.IpsRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IpsController {

    @Autowired
    private IpsRepository ipsRepository;

    @GetMapping("/ips")
    public String ips(Model model) {
        model.addAttribute("ips", ipsRepository.darAllIps());
        return model.toString();
    }
    
    @GetMapping("/ips/new")
    public String ipsForm(Model model) {
        model.addAttribute("ips", new Ips());
        return "ipsForm";
    }
    
    @PostMapping("/ips/new/save")
    public String ipsGuardar(@RequestBody Ips ips) {
        ipsRepository.insertarIps(ips.getNit(), ips.getNombre(), ips.getTipo(), ips.getDireccion(), ips.getTelefono());
        return "redirect:/ips";
    }
    
    @GetMapping("/ips/{id}/edit")
    public String ipsEditarForm(@PathVariable("id") Integer id, Model model) {
        Ips ips = ipsRepository.darIps(id);
        if (ips != null) {
            model.addAttribute("ips", ips);
            return "ipsEditForm";
        } else {
            return "redirect:/ips";
        }
    }

    @PostMapping("/ips/{id}/edit/save")
    public String ipsEditarGuardar(@PathVariable("id") Integer id, @ModelAttribute Ips ips) {
        ipsRepository.actualizarIps(id, ips.getNombre(), ips.getTipo(), ips.getDireccion(), ips.getTelefono());
        return "redirect:/ips";
    }

    @GetMapping("/ips/{id}/delete")
    public String ipsEliminar(@PathVariable("id") Integer id) {
        ipsRepository.eliminarIps(id);
        return "redirect:/ips";
    }



}
