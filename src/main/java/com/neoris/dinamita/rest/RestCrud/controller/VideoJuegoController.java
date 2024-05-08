package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
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
            List<VideoJuego> lista = videoJuegoService.listarVideoJuegos();
            if(lista.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La lista de Video Juegos esta vacia");
            }else{
                return ResponseEntity.ok().body(lista);
            }
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/insertar")
    public ResponseEntity<String> insertarVideoJuego(@RequestBody VideoJuego videoJuego){
        try {
            System.out.println(videoJuego);
            if (videoJuegoService.insertarVideoJuego(videoJuego)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Videojuego insertado: " + videoJuego);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe el video juego con titulo: " + videoJuego.getTitulo());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("No se ha podido insertar la Persona: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/buscarVideojuego")
    public ResponseEntity<Object> buscarVideoJueg(@RequestParam Integer id) {
        VideoJuego videoJuego = videoJuegoService.buscarVideoJuego(id);
        if (videoJuego != null) {
            return ResponseEntity.ok().body(videoJuego);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Video juego no encontrado");
        }
    }

    @DeleteMapping("/eliminarVideoJuego")
    public ResponseEntity<String> eliminarVideoJuego(@RequestParam Integer id) {
        boolean eliminado = videoJuegoService.eliminarVideoJuego(id);
        if (eliminado) {
            return ResponseEntity.status(HttpStatus.OK).body("Video Juego eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar video juego");
        }
    }

    @PutMapping("/modificarVideoJuego")
    public ResponseEntity<String> modificarPersona(@RequestParam Integer id, @RequestBody VideoJuego videoJuego){
        boolean editado = videoJuegoService.modificarVideoJuego(id, videoJuego);
        if(editado){
            return ResponseEntity.status(HttpStatus.OK).body("Video Juego modificado con exito");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al modificar el video juego");
        }
    }

    @GetMapping("/listaDesarrollador")
    public ResponseEntity<Object> listarVideoJuegos(@RequestParam String desarrolladora){
        try{
            List<VideoJuego> lista = videoJuegoService.listarVideoJuegosPorDesarrolladora(desarrolladora);
            if(lista.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existen video juegos de esa desarrolladora");
            }else{
                return ResponseEntity.ok().body(lista);
            }
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(null);
        }
    }


}
