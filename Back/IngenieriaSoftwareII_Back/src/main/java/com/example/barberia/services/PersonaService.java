package com.example.barberia.services;

import java.util.List;
import java.util.Optional;

import com.example.barberia.model.Persona;

public interface PersonaService {
    List<Persona> getAll();
    Optional<Persona> findById(Long id);
    Persona insert(Persona persona);
    Optional<Persona> findByEmailAndPassword(String email, String password);
    Optional<Persona> findByEmail(String email);
    Optional<Persona> findByUsername(String username);
    Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);

}