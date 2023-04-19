package com.cursoudemy.springboot.backend.apirest.controladores;

import com.cursoudemy.springboot.backend.apirest.entidades.Factura;
import com.cursoudemy.springboot.backend.apirest.servicios.ClienteServicioImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api")
public class FacturaControlador {

    @Autowired
    private ClienteServicioImpl clienteServicio;

    final static Logger log = LoggerFactory.getLogger(FacturaControlador.class);

    @GetMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Factura show(@PathVariable Long id){

        return clienteServicio.findFacturaById(id);

    }

}