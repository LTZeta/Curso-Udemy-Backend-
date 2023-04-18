package com.cursoudemy.springboot.backend.apirest.servicios;

import com.cursoudemy.springboot.backend.apirest.entidades.Cliente;
import com.cursoudemy.springboot.backend.apirest.entidades.Region;
import com.cursoudemy.springboot.backend.apirest.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicioInterfaz{

    @Autowired
    ClienteRepositorio clienteRepositorio;
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepositorio.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepositorio.deleteById(id);
    }

    @Override
    @Transactional
    public List<Region> findAllRegiones() {
        return clienteRepositorio.findAllRegiones();
    }
}
