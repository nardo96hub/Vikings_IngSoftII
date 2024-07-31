package com.example.barberia.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class Persona {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;
 private String username;
 private String email;
 private String password;
 @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "persona_roles", 
				joinColumns = @JoinColumn(name = "persona_id"), 
				inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();
    
    public Persona() {
	}

	public Persona(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
}