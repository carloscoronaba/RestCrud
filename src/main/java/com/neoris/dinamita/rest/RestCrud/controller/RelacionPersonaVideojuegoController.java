package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import com.neoris.dinamita.rest.RestCrud.service.IRelacionPersonaVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relacion")
public class RelacionPersonaVideojuegoController {

    @Autowired
    IRelacionPersonaVideojuego relacionPersonaVideojuego;

    @PostMapping("/agregar")
    public ResponseEntity<Object> agregarVideojuego(@RequestParam String email, @RequestParam String titulo){
        try {
            if (relacionPersonaVideojuego.asignarPersonaVideojuego(email,titulo)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Videojuego insertado: " + titulo +
                        " email: " + email);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al agregar videojuego");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al agregar videojuego: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Object> eliminarVideojuego(@RequestParam String email, @RequestParam String titulo){
        try {
            if (relacionPersonaVideojuego.eliminarVideojuegoPersona(email,titulo)) {
                return ResponseEntity.status(HttpStatus.OK).body("Videojuego eliminado: " + titulo +
                        " email: " + email);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al eliminar videojuego");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al eliminar videojuego: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
