package com.cursoudemy.springboot.backend.apirest.servicios;

import com.cursoudemy.springboot.backend.apirest.entidades.Cliente;
import com.cursoudemy.springboot.backend.apirest.entidades.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteServicioInterfaz {

    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);
    public Cliente findById(Long id);
    public Cliente save(Cliente cliente);
    public void delete(Long id);

    public List<Region> findAllRegiones();

}