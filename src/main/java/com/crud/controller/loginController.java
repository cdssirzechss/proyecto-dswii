package com.crud.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

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
        model.addAttribute("userEmail", userEmail);
        return "index"; 
	}
	
}
