package com.example.barberia.services.impl;

import java.util.List;
import java.util.Optional;

import com.example.barberia.model.Persona;
import com.example.barberia.repositories.PersonaRepository;
import com.example.barberia.services.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository repo;

	@Override
	public List<Persona> getAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Persona> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public Persona insert(Persona persona) {
		return repo.save(persona);
	}

	@Override
	public Optional <Persona> findByEmailAndPassword(String email, String password) {
		return repo.findByEmailAndPassword(email, password);
	}

	@Override
	public Optional<Persona> findByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public Optional<Persona> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return repo.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return repo.existsByEmail(email);
	}

}