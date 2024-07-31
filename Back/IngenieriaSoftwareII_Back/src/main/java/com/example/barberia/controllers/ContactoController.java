package com.example.barberia.controllers;

import com.example.barberia.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.barberia.model.Contacto;
import com.example.barberia.services.ContactoService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/contactos")
@CrossOrigin
public class ContactoController {

    @Autowired
	private ContactoService service;
    
    @PostMapping
    public Contacto save(@RequestBody Contacto contacto){
        return service.insert(contacto);
    }

    @GetMapping
	public List<Contacto> getMensajes() {
		return service.getAll();
    }

    @DeleteMapping
    public ResponseEntity<MessageResponse> delete(@RequestBody Contacto contacto){
        service.delete(contacto);
        return new ResponseEntity<>(new MessageResponse("Contacto eliminado exitosamente"), HttpStatus.OK);
    }
}