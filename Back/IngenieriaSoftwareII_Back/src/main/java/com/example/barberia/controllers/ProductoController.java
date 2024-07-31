package com.example.barberia.controllers;

import java.util.List;

import com.example.barberia.model.Producto;
import com.example.barberia.payload.response.MessageResponse;
import com.example.barberia.services.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin
public class ProductoController {

    @Autowired
	private ProductoService service;
    
    @PostMapping
    public Producto save(@RequestBody Producto producto){
        return service.insert(producto);
    }

    @GetMapping
	public List<Producto> getProductos() {
		return service.getProductos();
    }

    @PutMapping
    public Producto editarProducto(@RequestBody Producto producto){return service.update(producto);}

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> borrarProductos(@PathVariable long id){
        service.borrarProductos(id);
        return new ResponseEntity<>(new MessageResponse("Producto eliminado exitosamente"), HttpStatus.OK);
    }
}
