package com.example.barberia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Turno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String email;
  private String barbero;
  private int dia;
  private int mes;
  private String hora;
  private boolean disponible;

  public Turno(){}
  public Turno(String user,String email,String barb,int dia,int mes,String hora,boolean disp){
     this.username=user;
     this.email=email;
     this.barbero=barb;
     this.dia=dia;
     this.mes=mes;
     this.hora=hora;
     this.disponible=disp;
  }
}