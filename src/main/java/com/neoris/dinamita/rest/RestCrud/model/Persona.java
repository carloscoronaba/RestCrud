package com.neoris.dinamita.rest.RestCrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private int edad;
    private String email;

    @Override
    public String toString() {
        return id +
                "," + nombre +
                "," + apellido +
                "," + edad +
                "," + email;
    }

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "personas_videojuegos",
            joinColumns = @JoinColumn(name = "persona_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "videojuego_id", referencedColumnName = "ID_JUEGO")
    )
    private List<VideoJuego> videojuegos = new ArrayList<>();
    

}
