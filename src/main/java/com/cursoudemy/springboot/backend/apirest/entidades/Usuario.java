package com.cursoudemy.springboot.backend.apirest.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Accessors(chain = true)
@Table(name="usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, length = 20)
    private String username;

    @NotNull
    @Column(length = 60)
    private String password;

    private String nombre;

    private String apellido;

    @Column(unique = true)
    private String email;

    private Boolean enabled;

    /**
     La anotación @JoinTable nos permite modificar mediante atributos el nombre de los campos y de la tabla, por ejemplo con joinColumns podemos
     asignarle un nombre personalizado al campo proveniente de la tabla dueña de la relación, "usuario_id" (el que viene por defecto),
     y con inverseJoinColumns podemos hacer lo propio con la tabla a la cual se va a relacionar que por defecto sería "role_id"
     Con el atributo uniqueConstraints podemos indicar que no queremos que se repitan relaciones iguales entre usuarios y roles,
     es decir, si un usuario ya tiene asignado un rol, no queremos que se vuelva a asignar, queremos que cada asignación sea única
     por ejemplo: "usuario_id = 1, role_id = 1"
     en caso de que se vaya a repetir que el usuario con ID 1 y role con ID 1 se vuelvan a relacionar con este atributo lo vamos a impedir,
     debido a que ya existe la relación
     **/

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Role> roles;

}