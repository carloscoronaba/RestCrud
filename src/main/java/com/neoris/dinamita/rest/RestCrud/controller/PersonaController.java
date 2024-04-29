package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.modelo.Persona;
import com.neoris.dinamita.rest.RestCrud.servicio.IPersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud")
public class PersonaController {

    @Autowired
    IPersonaServicio servicio;

    @PostMapping("/insertar")
    public String insertarPersona(@RequestBody Persona persona){

        try{
            servicio.insertarPersona(persona);
            System.out.println(persona);
            return "Persona Ingresada con exito: " + persona;

        }catch (Exception ex){
            System.out.println("No se ha podido insertar la Persona: " + ex.getMessage());
            return null;
        }

    }

    @GetMapping("/listaPersonas")
    public List<Persona> listarPersonas(){
        List<Persona> personas = servicio.listarPersonas();

        return personas;

    }

    @GetMapping("/buscarPersona")
    public Persona buscarPersona(@RequestParam String email){

        return servicio.buscarPersona(email);

    }

}
