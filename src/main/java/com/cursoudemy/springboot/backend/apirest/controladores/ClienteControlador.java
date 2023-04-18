package com.cursoudemy.springboot.backend.apirest.controladores;


import com.cursoudemy.springboot.backend.apirest.entidades.Cliente;
import com.cursoudemy.springboot.backend.apirest.entidades.Region;
import com.cursoudemy.springboot.backend.apirest.servicios.ClienteServicioImpl;
import com.cursoudemy.springboot.backend.apirest.servicios.UploadFileServicioImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ClienteControlador {

    @Autowired
    ClienteServicioImpl clienteServicio;
    @Autowired
    private UploadFileServicioImpl uploadFileServicio;

    final static Logger log = LoggerFactory.getLogger(ClienteControlador.class);
    @GetMapping("/clientes")
    public List<Cliente> index(){
        return clienteServicio.findAll();
    }

    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page){
        return clienteServicio.findAll(PageRequest.of(page, 4));
    }


    //Usamos @PathVariable para indicar que el ID que debe buscar se encuentra en la ruta

    //@Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Cliente cliente;
        Map<String, Object> response = new HashMap<>();

        try {
            cliente = clienteServicio.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cliente == null){
            response.put("mensaje", "El cliente ID: ".concat(id.toString()).concat(" No existe en la base de datos!"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }


    //Usamos @RequestBody porque los datos van a estar dentro del cuerpo de la petición, por ende le indicamos mediante esta anotación
    //Que busque, encuentre y mapee los datos a la entidad Cliente.

    @Secured("ROLE_ADMIN")
    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result){
        Cliente clienteNew;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){

//            Esta seria la forma antigua de hacerlo
//            List<String> errores = new ArrayList<>();
//            for (FieldError error : result.getFieldErrors()){
//                errores.add("El campo '"+ error.getField() +"' "+error.getDefaultMessage());
//            }

            //Aca hacemos uso del Api Stream de Java.

            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> "El campo '"+ e.getField() +"' "+e.getDefaultMessage())
                    .toList();

            response.put("errors", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            clienteNew = clienteServicio.save(cliente);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al ingresar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido ingresado con éxito");
        response.put("cliente", clienteNew);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id){
        Cliente clienteActual = clienteServicio.findById(id);
        Map<String, Object> response = new HashMap<>();
        Cliente clienteActualizado;

        if (result.hasErrors()){
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(e -> "El campo '"+ e.getField() +"' "+e.getDefaultMessage())
                    .toList();

            response.put("errors", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (clienteActual == null){
            response.put("mensaje", "Error: No se pudo editar, el cliente ID: ".concat(id.toString()).concat(" No existe en la base de datos"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {

            clienteActual.setNombre(cliente.getNombre())
                    .setApellido(cliente.getApellido())
                    .setEmail(cliente.getEmail())
                    .setCreateAt(cliente.getCreateAt())
                    .setRegion(cliente.getRegion());

            clienteActualizado = clienteServicio.save(clienteActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido actualizado con éxito");
        response.put("cliente", clienteActualizado);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        try {
            Cliente cliente = clienteServicio.findById(id);
            String nombreFotoAnterior = cliente.getFoto();

            uploadFileServicio.eliminar(nombreFotoAnterior);

            clienteServicio.delete(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente fue eliminado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = clienteServicio.findById(id);


        if (!archivo.isEmpty()){

            String nombreArchivo;

            try{
                nombreArchivo = uploadFileServicio.copiar(archivo);
            }catch (IOException e){
                response.put("mensaje", "Error al subir la imagen del cliente");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String nombreFotoAnterior = cliente.getFoto();

            uploadFileServicio.eliminar(nombreFotoAnterior);

            cliente.setFoto(nombreArchivo);
            clienteServicio.save(cliente);

            response.put("cliente", cliente);
            response.put("mensaje", "La imagen se ha subido correctamente");
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

        Resource recurso;

        try {
            recurso= uploadFileServicio.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al cargar la imagen");
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("/clientes/regiones")
    public List<Region> listarRegiones(){
        return clienteServicio.findAllRegiones();
    }

}