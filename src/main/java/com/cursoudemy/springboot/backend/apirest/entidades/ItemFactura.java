package com.cursoudemy.springboot.backend.apirest.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Accessors(chain = true)
@Table(name="facturas_items")
public class ItemFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto producto;

    public Double getImporte(){
        return cantidad.doubleValue()*producto.getPrecio();
    }

}