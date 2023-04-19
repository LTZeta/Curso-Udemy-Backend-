package com.cursoudemy.springboot.backend.apirest.repositorios;

import com.cursoudemy.springboot.backend.apirest.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

    public Usuario findByUsername(String username);

    //En caso de querer agregar más condiciones o filtros usamos And

    //public Usuario findByUsernameAndEmail(String username, String email);

}