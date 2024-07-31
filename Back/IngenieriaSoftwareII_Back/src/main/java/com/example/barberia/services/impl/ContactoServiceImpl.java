package com.example.barberia.services.impl;

import java.util.List;

import com.example.barberia.model.Contacto;
import com.example.barberia.repositories.ContactoRepository;
import com.example.barberia.services.ContactoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoServiceImpl implements ContactoService {
    @Autowired
    private ContactoRepository repo;

    @Override
    public Contacto insert(Contacto contacto) {
        return repo.save(contacto);
    }

    @Override
    public void delete(Contacto cont) {
        repo.delete(cont);
    }

    @Override
    public List<Contacto> getAll() {
        return repo.findAll();
    }    
}