package com.example.barberia.services;

import java.util.List;

import com.example.barberia.model.Contacto;

public interface ContactoService {
    Contacto insert(Contacto contacto);

    void delete(Contacto cont);
	List<Contacto> getAll();
}