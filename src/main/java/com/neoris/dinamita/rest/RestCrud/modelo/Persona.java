package com.neoris.dinamita.rest.RestCrud.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private int edad;
    private String email;

    /*public Persona(){
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }*/

    @Override
    public String toString() {
        return id +
                "," + nombre +
                "," + apellido +
                "," + edad +
                "," + email;
    }

}
