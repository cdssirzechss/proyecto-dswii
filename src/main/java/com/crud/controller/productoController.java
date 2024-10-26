package com.crud.controller;

import com.crud.model.producto;
import com.crud.model.usuario;
import com.crud.repository.productoRepository;
import com.crud.servicios.productoService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class productoController {

	@Autowired
	private productoRepository prodrepo;
	
    @Autowired
    private productoService prodService;
    
    @ModelAttribute("producto")
	public producto model() {
		return new producto();
	}

    @GetMapping("/vista")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", prodService.findAll()); // Lista de productos
        return "producto";  // Vista para mostrar lista de productos
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        return "formProducto"; 
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute producto producto, Model model) {
        Optional<producto> existingProduct = prodrepo.findByNombre(producto.getNombre());
        
        if (existingProduct.isPresent()) {

            model.addAttribute("mensaje", "Error: Ya existe un producto con el nombre: " + producto.getNombre() + ". Por favor, elige otro nombre.");

            model.addAttribute("producto", producto);
            return "formProducto";  
            
        }

        prodService.create(producto);
        model.addAttribute("mensaje", "Producto creado exitosamente.");
        
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
