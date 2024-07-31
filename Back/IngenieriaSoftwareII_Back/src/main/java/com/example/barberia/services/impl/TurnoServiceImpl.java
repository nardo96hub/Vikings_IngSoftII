package com.example.barberia.services.impl;

import java.util.List;

import com.example.barberia.model.Turno;
import com.example.barberia.repositories.TurnoRepository;
import com.example.barberia.services.TurnoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoRepository repo;

    @Override
    public Turno insert(Turno turno) {
        return repo.save(turno);
    }

    @Override
    public List<Turno> getAll() {
        return repo.findAll();
    }
    
    @Override
    public void eliminarPorID(Long id) {
        repo.deleteById(id);
    }
   
}