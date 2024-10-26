package com.crud.servicios.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.model.rol;
import com.crud.repository.rolRepository;
import com.crud.servicios.rolService;

@Service
public class rolServiceImple implements rolService{

	@Autowired
	private rolRepository rolrepo;
	
	@Override
	public rol buscarrolporid(Long id) {
		return rolrepo.findById(id).orElseThrow(() -> new RuntimeException("rol no encontrado con ID: " + id));
	}
	
	

}
