package com.neoris.dinamita.rest.RestCrud.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public class Persona {

    private String id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;

    public Persona(){
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public String toString() {
        return id +
                "," + nombre +
                "," + apellido +
                "," + edad +
                "," + email;
    }
}
