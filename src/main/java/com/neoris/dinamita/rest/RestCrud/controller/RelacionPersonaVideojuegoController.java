package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.service.IRelacionPersonaVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relacion")
public class RelacionPersonaVideojuegoController {

    @Autowired
    IRelacionPersonaVideojuego relacionPersonaVideojuego;

    @PostMapping("/agregar")
    public ResponseEntity<Object> agregarVideojuego(@RequestParam String email, @RequestParam String titulo){
        relacionPersonaVideojuego.asignarPersonaVideojuego(email,titulo);
        return ResponseEntity.ok("Videokjuego agragado con exito");
    }
}
