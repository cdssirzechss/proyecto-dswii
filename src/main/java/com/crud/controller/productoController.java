package com.crud.controller;

import com.crud.model.producto;
import com.crud.servicios.productoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/producto")
public class productoController {

    @Autowired
    private productoService prodService;

    @GetMapping("/vista")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", prodService.findAll()); // Lista de productos
        return "producto";  // Vista para mostrar lista de productos
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new producto()); 
        return "formProducto"; 
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute producto producto) {
        prodService.create(producto);
        return "redirect:/producto/vista";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        producto prod = prodService.findById(id);
        if (prod != null) {
            model.addAttribute("producto", prod); // Producto encontrado
            return "formProducto";  // Vista de formulario de edición
        } else {
            return "redirect:/producto/vista"; // Redirecciona si no se encuentra el producto
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        prodService.delete(id);
        return "redirect:/producto/vista";
    }
}
