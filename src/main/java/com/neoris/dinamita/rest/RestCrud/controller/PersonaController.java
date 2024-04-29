package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.modelo.Persona;
import com.neoris.dinamita.rest.RestCrud.servicio.IPersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crud")
public class PersonaController {

    @Autowired
    IPersonaServicio servicio;

    @PostMapping("/insertar")
    public String insertarPersona(@RequestBody Persona persona){

        try{
            servicio.insertarPersona(persona);

            return "Persona Ingresada con exito: " + persona.getNombre();

        }catch (Exception ex){
            System.out.println("No se ha podido insertar la Persona: " + ex.getMessage());
            return null;
        }

    }

}
