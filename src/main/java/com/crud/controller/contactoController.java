package com.crud.controller;

import com.crud.model.contacto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contacto")
public class contactoController {

    @GetMapping
    public String mostrarContacto(Model model) {
        model.addAttribute("contacto", new contacto());
        return "contacto";
    }

    @PostMapping
    public String enviarContacto(@ModelAttribute contacto contacto, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensaje", "Tu mensaje ha sido enviado con éxito.");
        return "redirect:/contacto"; 
    }
}
