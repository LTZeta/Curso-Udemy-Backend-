package com.cursoudemy.springboot.backend.apirest.repositorios;

import com.cursoudemy.springboot.backend.apirest.entidades.Factura;
import org.springframework.data.repository.CrudRepository;

public interface FacturaRepositorio extends CrudRepository<Factura, Long> {

}