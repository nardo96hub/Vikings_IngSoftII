package com.example.barberia.services;

import java.util.List;

import com.example.barberia.model.Turno;

public interface TurnoService {

	Turno insert(Turno turno);

	List<Turno> getAll();
    
	void eliminarPorID(Long id);
}