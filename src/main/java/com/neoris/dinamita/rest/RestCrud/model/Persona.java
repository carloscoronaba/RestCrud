package com.neoris.dinamita.rest.RestCrud.model;

import jakarta.persistence.*;
import lombok.*;

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
