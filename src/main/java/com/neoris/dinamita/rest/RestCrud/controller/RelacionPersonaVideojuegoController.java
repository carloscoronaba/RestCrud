package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import com.neoris.dinamita.rest.RestCrud.service.IRelacionPersonaVideojuego;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relacion")
public class RelacionPersonaVideojuegoController {

    @Autowired
    IRelacionPersonaVideojuego relacionPersonaVideojuego;

    @Operation(summary = "Post para insertar un videojuego a una persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "juego asignado"),
            @ApiResponse(responseCode = "409", description = "error al agregar videojuego"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
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

    @Operation(summary = "Delete para eliminar un videojuego a persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "videojuego eliminado"),
            @ApiResponse(responseCode = "409", description = "error al eliminar videojuego"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
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

    @Operation(summary = "Put para editar los videojuegos de una persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "videojuego editado"),
            @ApiResponse(responseCode = "409", description = "no se encontro el videojuego"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @PutMapping("/editar")
    public ResponseEntity<String> editarVideoJuego(@RequestParam String email, @RequestParam String tituloEditar, @RequestParam String tituloNuevo){

        try {
            if (relacionPersonaVideojuego.editarRegistroVideojuegoPersona(email, tituloEditar, tituloNuevo)) {
                return ResponseEntity.status(HttpStatus.OK).body("Videojuego editado correctamente: " + tituloNuevo +
                        " de la persona con email: " + email);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("No se encontro el video juego a editar");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al editar el videojuego: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @Operation(summary = "Get para listar los juegos de la persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "persona encontrada"),
            @ApiResponse(responseCode = "409", description = "correo no existe")
    })
    @GetMapping("/listarJuegosPersona")
    public ResponseEntity<List<VideoJuego>> listarVideoJuegosPersona(@RequestParam String email){

        List<VideoJuego> listaVideojuegos = relacionPersonaVideojuego.listaVideoJuegosPorPersona(email);
        if (listaVideojuegos.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok().body(listaVideojuegos);
        }
    }
}
