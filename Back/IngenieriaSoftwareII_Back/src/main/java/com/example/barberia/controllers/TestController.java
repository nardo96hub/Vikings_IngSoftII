package com.example.barberia.controllers;

import com.example.barberia.payload.response.MessageResponse;
import com.example.barberia.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	private TestService testServi;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	@GetMapping("/all-info")
	public ResponseEntity<MessageResponse> getInfo() {
		if (testServi.setAllInfo()) return new ResponseEntity<>(new MessageResponse("Se Agrego Informacion"), HttpStatus.OK);

		return new ResponseEntity(new MessageResponse("Ya hay Info en la BDD"),HttpStatus.OK);

	}
}