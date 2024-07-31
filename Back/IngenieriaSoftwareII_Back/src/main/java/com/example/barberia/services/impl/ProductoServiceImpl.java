package com.example.barberia.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.barberia.model.Producto;
import com.example.barberia.repositories.ProductoRepository;
import com.example.barberia.services.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public Producto insert(Producto producto) {return repo.save(producto);}

    @Override
    public List<Producto> getProductos() {
        return repo.findAll();
    }

    @Override
    public void borrarProductos(long id){
        repo.deleteById(id);
    }

    @Override
    public Producto update(Producto producto) {

        Optional<Producto> update = repo.findById(producto.getId());
        if (update.isPresent()){
            Producto updateProd = update.get();

            updateProd.setCantidad(producto.getCantidad());
            updateProd.setCoste(producto.getCoste());
            updateProd.setPrecio(producto.getPrecio());
            updateProd.setDescripcion(producto.getDescripcion());
            updateProd.setNombre(producto.getNombre());
            updateProd.setDisponible(producto.isDisponible());
            updateProd.setProveedor(producto.getProveedor());

            return repo.save(updateProd);
        }

        return null;
    }

}
