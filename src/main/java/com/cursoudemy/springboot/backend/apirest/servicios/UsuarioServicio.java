package com.cursoudemy.springboot.backend.apirest.servicios;

import com.cursoudemy.springboot.backend.apirest.entidades.Usuario;

public interface UsuarioServicio {

    public Usuario findByUsername(String username);

}