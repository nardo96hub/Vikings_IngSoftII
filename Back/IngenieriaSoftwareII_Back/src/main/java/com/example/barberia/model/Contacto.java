package com.example.barberia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Contacto {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;
 private String nombre;
 private String apellido;
 private String email;
 private String telefono;
 private String comentario;

 public Contacto(){}
 public Contacto(String n,String a,String e,String t,String c){
   this.nombre = n;
   this.apellido = a;
   this.email = e;
   this.telefono = t;
   this.comentario = c;
 }
}