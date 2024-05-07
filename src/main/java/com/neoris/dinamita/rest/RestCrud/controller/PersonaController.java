package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud")
public class PersonaController {

    @Autowired
    IPersonaService servicio;

    @PostMapping("/insertar")
    public ResponseEntity<String> insertarPersona(@RequestBody Persona persona){
        try {
            System.out.println(persona);
            if (servicio.insertarPersona(persona)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Persona Ingresada con exito: " + persona);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Correo electronico ya existe: " + persona.getEmail());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("No se ha podido insertar la Persona: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    @GetMapping("/listaPersonas")
    public ResponseEntity<Object>listarPersonas(){
        try{
            List<Persona> personas = servicio.listarPersonas();
            if(personas.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La lista esta vacia");
            }else{
                return ResponseEntity.ok().body(personas);
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
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

    @DeleteMapping("/eliminarPersona")
    public ResponseEntity<String> eliminarPersona(@RequestParam String email) {
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
