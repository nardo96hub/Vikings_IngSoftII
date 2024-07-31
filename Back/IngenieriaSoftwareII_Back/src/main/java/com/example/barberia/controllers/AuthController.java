package com.example.barberia.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.barberia.payload.request.LoginRequest;
import com.example.barberia.model.ERol;
import com.example.barberia.model.Persona;
import com.example.barberia.model.Rol;
import com.example.barberia.payload.request.SignupRequest;
import com.example.barberia.payload.response.JwtResponse;
import com.example.barberia.payload.response.MessageResponse;
import com.example.barberia.repositories.PersonaRepository;
import com.example.barberia.repositories.RolRepository;
import com.example.barberia.security.jwt.JwtUtils;
import com.example.barberia.services.impl.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PersonaRepository userRepository;

	@Autowired
	RolRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
				
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		log.info(jwt);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
				
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Nombre de usuario ya esta en uso!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email ya esta en uso!"));
		}

		// Create new user's account
        Persona user = new Persona(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRol();
		Set<Rol> roles = new HashSet<>();

		if (strRoles == null && (!user.getUsername().contains("admin") && !user.getUsername().contains("barbero"))) {
			Rol userRole = roleRepository.findByName(ERol.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

			roles.add(userRole);
		} else {
			if (strRoles == null && user.getUsername().contains("admin")) {
				Rol adminRole = roleRepository.findByName(ERol.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

				roles.add(adminRole);
			} else{
				if (strRoles == null && user.getUsername().contains("barbero")){
					Rol empleadoRole = roleRepository.findByName(ERol.ROLE_EMPLEADO)
							.orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

					roles.add(empleadoRole);
				} else {
					strRoles.forEach(role -> {
						if (role.contains("admin")){
							Rol adminRole = roleRepository.findByName(ERol.ROLE_ADMIN)
									.orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

							roles.add(adminRole);
						} else {
							Rol userRole = roleRepository.findByName(ERol.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
							roles.add(userRole);
						}

					});
				}
			}
		}
		
		user.setRoles(roles);

		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Usuario registrado exitosamente!"));
	}
}