package com.cursoudemy.springboot.backend.apirest.controladores;

import com.cursoudemy.springboot.backend.apirest.entidades.Factura;
import com.cursoudemy.springboot.backend.apirest.entidades.Producto;
import com.cursoudemy.springboot.backend.apirest.servicios.ClienteServicioImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api")
public class FacturaControlador {

    @Autowired
    private ClienteServicioImpl clienteServicio;

    final static Logger log = getLogger(FacturaControlador.class);

    @GetMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Factura show(@PathVariable Long id){

        return clienteServicio.findFacturaById(id);

    }

    @DeleteMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clienteServicio.deleteFacturaById(id);
    }

    @GetMapping("/facturas/filtrar-productos/{term}")
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> filtrarProductos(@PathVariable String term){
        return clienteServicio.findProductoByNombre(term);
    }

}