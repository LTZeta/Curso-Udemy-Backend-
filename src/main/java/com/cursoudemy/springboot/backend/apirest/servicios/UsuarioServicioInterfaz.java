package com.cursoudemy.springboot.backend.apirest.servicios;

import com.cursoudemy.springboot.backend.apirest.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioServicioInterfaz {

    public Usuario findByUsername(String username);

}
