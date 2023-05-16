package com.cursoudemy.springboot.backend.apirest.repositorios;

import com.cursoudemy.springboot.backend.apirest.entidades.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRepositorio extends CrudRepository<Producto, Long> {

    public List<Producto> findByNombreStartingWithIgnoreCase(String term);

    public List<Producto> findByNombreContainingIgnoreCase(String term);
}
