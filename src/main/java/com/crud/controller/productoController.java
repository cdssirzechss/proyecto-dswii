package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.crud.model.producto;
import com.crud.servicios.productoService;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class productoController {

    @Autowired
    private productoService prodService;

    @PostMapping("/crear")
    public producto crear(@RequestBody producto producto) {
        return prodService.create(producto);
    }

    @GetMapping("/listar")
    public List<producto> listar() {
        return prodService.findAll();
    }

    @GetMapping("/{id}")
    public producto obtenerPorId(@PathVariable Long id) {
        return prodService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        prodService.delete(id);
    }
}
