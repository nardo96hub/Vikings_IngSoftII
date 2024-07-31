package com.example.barberia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String descripcion;
    private float precio;
    private boolean disponible;
    private float coste;
    private int cantidad;
    private String proveedor;
    private String imagen;

    public Producto(){}
    public Producto(String nom, String des, float pre, boolean dis, float cos, int cant, String prov,String imagen){
        this.nombre=nom;
        this.descripcion=des;
        this.precio=pre;
        this.coste=cos;
        this.cantidad=cant;
        this.proveedor=prov;
        this.disponible=dis;
        this.imagen = imagen;
    }
}
