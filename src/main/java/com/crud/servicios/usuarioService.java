package com.crud.servicios;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.crud.model.usuario;

public interface usuarioService extends UserDetailsService{
	
	public List<usuario>  listaporrol (String nombre); 
	
	public usuario obtenerusuarioporid(Long id);
	
	public void eliminar(Long id);

}
