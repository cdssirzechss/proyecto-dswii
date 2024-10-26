package com.crud.servicios;

import java.util.List;
import com.crud.model.producto;

public interface productoService {
	
	producto create(producto producto);

	List<producto> findAll();

	producto findById(Long id);

	void delete(Long id);
}
