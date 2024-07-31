package com.example.barberia.repositories;

import java.util.Optional;

import com.example.barberia.model.ERol;
import com.example.barberia.model.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
	Optional<Rol> findByName(ERol name);
    
}