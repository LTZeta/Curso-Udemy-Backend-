package com.cursoudemy.springboot.backend.apirest.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Accessors(chain = true)
@Table(name="facturas")
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private String observacion;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"facturas", "hibernateLazyInitializer", "handler"})
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ItemFactura> items;

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public Double getTotal(){
        Double total = 0.00;
        for (ItemFactura item: items){
            total += item.getImporte();
        }
        return total;
    }
}
