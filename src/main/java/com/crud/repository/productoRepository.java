package com.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.model.producto;

public interface productoRepository extends JpaRepository<producto, Long> {
	
	Optional<producto> findByNombre(String nombre);
	
}
