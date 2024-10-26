package com.crud.servicios.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.model.producto;
import com.crud.repository.productoRepository;
import com.crud.servicios.productoService;

@Service
public class productoServiceImple implements productoService {

    @Autowired
    private productoRepository prodRepo;

    @Override
    public producto create(producto producto) {
    	return prodRepo.save(producto);
    }

    @Override
    public List<producto> findAll() {
        return prodRepo.findAll();
    }

    @Override
    public producto findById(Long id) {
        return prodRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        prodRepo.deleteById(id);
    }
	
}
