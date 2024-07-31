package com.example.barberia.controllers;

//import model.aus.ingsoftii.barberia.Usuario;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import lombok.extern.slf4j.Slf4j;

//import org.springframework.web.bind.annotation.RequestMapping;

@RestController
//@GetMapping(path = "/usuario")
//@Slf4j

public class UsuarioController{
    @GetMapping("/{hola}")
    public String holaM(){
        return "Hola Mundo";
    }

/*public class UsuarioController {

    //@GetMapping("/{id}")
    public Usuario metodoGet(@PathVariable(name = "id")String id){
        log.info("Ingresando a metodoGet con id {}", id);
        Usuario u1 = new Usuario();
        u1.setId(Long.valueOf(id));
        u1.setApellido("Bayard");
        u1.setNombre("Diego");
        u1.setEmail("dbayard@algo");
        u1.setTelefono("4444444");
        log.info("Devolviendo el usuario");
        return u1;
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuario;
    }*/
}