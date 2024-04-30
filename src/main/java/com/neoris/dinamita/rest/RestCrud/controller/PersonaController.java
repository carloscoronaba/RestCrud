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
            System.out.println(persona);
            if(servicio.insertarPersona(persona)){
                return "Persona Ingresada con exito: " + persona;
            }else{
                return "Correo electronico ya existe: " + persona.getEmail();
            }

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

    @DeleteMapping("/eliminarPersona/{email}")
    public String eliminarPersona(@PathVariable String email){
        boolean eliminado = servicio.eliminarPersona(email);
        if(eliminado){
            return "Persona eliminada con exito";
        }else{
            return "Error al eliminar persona";
        }
    }

    @PutMapping("/modificarPersona")
    public String modificarPersona(@RequestParam String email, @RequestBody Persona persona){
        boolean editado = servicio.modificarPersona(email, persona);
        if(editado){
            return "Persona modificada con exito";
        }else{
            return "Hubo un error al editar";
        }

    }
}
