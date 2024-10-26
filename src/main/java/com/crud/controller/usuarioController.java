package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.model.rol;
import com.crud.model.usuario;
import com.crud.servicios.implementacion.rolServiceImple;
import com.crud.servicios.implementacion.usuarioServiceImple;

@Controller
@RequestMapping("/usuario")
public class usuarioController {
	
	@Autowired
	public usuarioServiceImple usuService;
	
	@Autowired
	public rolServiceImple rolService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@ModelAttribute("usuario")
	public usuario modelousu() {
		return new usuario();
	}
	
	@GetMapping("/lista")
	public String ListaUsuario(Model model) {
		
		model.addAttribute("listausu", usuService.listaporrol("usuario"));
		
		return "ListaUsuario";
	}
	
	@GetMapping("/registro")
	public String MuestraRegistro(Model model) {
		
		rol rol = rolService.buscarrolporid((long)2);
		
		model.addAttribute("contra", (boolean) true);
		model.addAttribute("rol", rol);
		
		return "registro";
	}
	
	@PostMapping("/registro")
	public String Registro(@ModelAttribute("usuario") usuario usuario) {
		
		if(usuario.getId() != null) {
			
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuService.create(usuario);
			
			return "redirect:/usuario/lista?editado";
			
		}else {
		
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuService.create(usuario);
		
		return "redirect:/usuario/lista?exito";
		
		}
	}
	
	@GetMapping("/{id}/editar")
	public String MuestraEdicion(@PathVariable Long id, Model model) {
		
		usuario usu = usuService.obtenerusuarioporid(id);
		rol rol = rolService.buscarrolporid(usu.getRol().getId());
		
		model.addAttribute("editar", (boolean) true);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usu);
		
		return "registro";
	}
	
	@PostMapping("{id}/eliminar")
	public String Eliminar(@PathVariable Long id) {
		
		usuService.eliminar(id);
		
		return "redirect:/usuario/lista?eliminar";
	}

}
