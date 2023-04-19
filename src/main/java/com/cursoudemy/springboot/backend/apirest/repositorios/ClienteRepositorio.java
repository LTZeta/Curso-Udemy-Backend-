package com.cursoudemy.springboot.backend.apirest.repositorios;

import com.cursoudemy.springboot.backend.apirest.entidades.Cliente;
import com.cursoudemy.springboot.backend.apirest.entidades.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    @Query("from Region")
    public List<Region> findAllRegiones();

}