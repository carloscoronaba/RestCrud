package com.neoris.dinamita.rest.RestCrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "video_juegos")
public class VideoJuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_JUEGO")
    private Integer idJuego;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESARROLLADORA")
    private String desarrolladora;

    @Column(name = "LANZAMIENTO")
    private int lanzamiento;

    @Column(name = "URL_PORTADA")
    private String urlPortada;

    //@JsonIgnore
    //@ManyToMany(mappedBy = "videojuegos")
    //private List<Persona> personas = new ArrayList<>();

}
