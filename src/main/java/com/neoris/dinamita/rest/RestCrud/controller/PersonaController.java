package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.modelo.Persona;
import com.neoris.dinamita.rest.RestCrud.servicio.IPersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud")
public class PersonaController {

    @Autowired
    IPersonaServicio servicio;

    @PostMapping("/insertar")
    public ResponseEntity<String> insertarPersona(@RequestBody Persona persona){
        try {
            System.out.println(persona);
            if (servicio.insertarPersona(persona)) {
                return ResponseEntity.status(HttpStatus.OK).body("Persona Ingresada con exito: " + persona);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Correo electronico ya existe: " + persona.getEmail());
            }
        } catch (Exception ex) {
            System.out.println("No se ha podido insertar la Persona: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    @GetMapping("/listaPersonas")
    public ResponseEntity<List<Persona>>listarPersonas(){
        try{
            List<Persona> personas = servicio.listarPersonas();

            // Retorna la lista de personas con un estado HTTP 200 OK
            return new ResponseEntity<>(personas, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    @GetMapping("/buscarPersona")
    public ResponseEntity<Object> buscarPersona(@RequestParam String email) {
        Persona persona = servicio.buscarPersona(email);
        if (persona != null) {
            return ResponseEntity.ok().body(persona);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }

    @DeleteMapping("/eliminarPersona/{email}")
    public ResponseEntity<String> eliminarPersona(@PathVariable String email) {
        boolean eliminado = servicio.eliminarPersona(email);
        if (eliminado) {
            return ResponseEntity.status(HttpStatus.OK).body("Persona eliminada con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar persona");
        }
    }

    @PutMapping("/modificarPersona")
    public ResponseEntity<String> modificarPersona(@RequestParam String email, @RequestBody Persona persona){
        boolean editado = servicio.modificarPersona(email, persona);
        if(editado){
            return ResponseEntity.status(HttpStatus.OK).body("Persona modificada con exito");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al editar persona");
        }



    }


}
