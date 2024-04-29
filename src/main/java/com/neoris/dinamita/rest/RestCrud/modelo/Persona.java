package com.neoris.dinamita.rest.RestCrud.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@ToString
public class Persona {

    private int id = 0;
    private static int contadorPersona;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;

    public Persona(){
        this.id += contadorPersona;
    }

}
