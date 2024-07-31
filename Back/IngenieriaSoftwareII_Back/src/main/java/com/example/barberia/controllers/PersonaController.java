package com.example.barberia.controllers;
/*
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import model.aus.ingsoftii.barberia.Persona;
import services.aus.ingsoftii.barberia.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/personas")
@CrossOrigin
public class PersonaController {

    //@Autowired
    //private PersonaRepository repo;
    
    @Autowired
	private PersonaService service;
    
    @PostMapping
    public Persona insertarP(@RequestBody Persona persona){
        return service.insert(persona);
    }
    
    @GetMapping
	public List<Persona> getAll() {
		return service.getAll();
    }
    
    @PostMapping("/autenticacion")
    public Persona findByEmailAndPassword(@RequestBody Persona persona){
        return service.findByEmailAndPassword(persona.getEmail(), persona.getPassword()).orElseThrow(() -> new RuntimeException());
    }
}*/