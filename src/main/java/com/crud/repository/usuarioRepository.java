package com.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.model.usuario;

public interface usuarioRepository extends JpaRepository<usuario, Long>{
	
	public usuario findByEmail(String email);

	public List<usuario> findByRolNombre(String nombre);

}
