package com.neoris.dinamita.rest.RestCrud.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holamundo")
public class HolaMundo {

    @Operation(summary = "Get para obtener un Saludo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saludo exitoso"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @GetMapping("/saludo")
    public String saludar(@RequestParam String saludo) {
        System.out.println("Hola " + saludo + ", desde Consola");
        return "Hola " + saludo;
    }
}
