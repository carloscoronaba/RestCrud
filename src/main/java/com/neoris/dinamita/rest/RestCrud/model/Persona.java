package com.neoris.dinamita.rest.RestCrud.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "persona")
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

    @ManyToMany(mappedBy = "propietarios")
    private List<VideoJuego> videoJuegos;

}
