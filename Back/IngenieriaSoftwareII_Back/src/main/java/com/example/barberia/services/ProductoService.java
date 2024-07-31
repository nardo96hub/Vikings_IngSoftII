package com.example.barberia.services;

import java.util.List;

import com.example.barberia.model.Producto;

public interface ProductoService {

    Producto insert(Producto producto);

	List<Producto> getProductos();

    void borrarProductos(long id);

    Producto update(Producto producto);
    
}
