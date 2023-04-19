package com.cursoudemy.springboot.backend.apirest.servicios;

import com.cursoudemy.springboot.backend.apirest.entidades.Usuario;

public interface UsuarioServicioInterfaz {

    public Usuario findByUsername(String username);

}