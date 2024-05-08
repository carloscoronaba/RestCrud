package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import com.neoris.dinamita.rest.RestCrud.service.IPersonaService;
import com.neoris.dinamita.rest.RestCrud.service.IVideoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class VideoJuegoController {

    @Autowired
    IVideoJuegoService videoJuegoService;

    @GetMapping("/lista")
    public ResponseEntity<Object> listarVideoJuegos(){
        try{
            List<VideoJuego> lista = videoJuegoService.listarVideoJuego();
            if(lista.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La lista de Video Juegos esta vacia");
            }else{
                return ResponseEntity.ok().body(lista);
            }
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(null);
        }

        return ResponseEntity.ok().body(lista);
    }

    @PostMapping("/insertar")
    public ResponseEntity<String> insertarVideoJuego(@RequestBody VideoJuego videoJuego){
        try {
            System.out.println(videoJuego);
            if (videoJuegoService.insertarVideoJuego(videoJuego)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Videojuego insertado: " + videoJuego);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("No se pudo insertar (validar por nombre): " + videoJuego);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("No se ha podido insertar la Persona: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


}
