package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.crud.model.producto;

import com.crud.servicios.productoService;

@Controller
public class loginController {

    @Autowired
    private productoService productoService;
    
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
		
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Authentication auth,  Model modelo) {
		
		for (GrantedAuthority authority : auth.getAuthorities()) {
            if (authority.getAuthority().equals("admin")) {
                return "redirect:/admin";
            } else if (authority.getAuthority().equals("usuario")) {
                return "redirect:/usuario";
            } 
        }
		
		modelo.addAttribute("rol", auth.getAuthorities().toString());
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin_vista(Authentication auth, Model modelo) {
		
		modelo.addAttribute("rol", auth.getAuthorities().toString());

		return "admin";
	}

	@GetMapping("/usuario")
	public String usuario_vista(Authentication auth, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.isAuthenticated() ? authentication.getName() : null;

		List<producto> productos = productoService.findAll();
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("productos", productos);
		return "index";
	}
	
}
