package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.service.IPersonaService;
import com.neoris.dinamita.rest.RestCrud.util.ListarPersonasPdf;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/crud")
public class PersonaController {
    @Autowired
    private ListarPersonasPdf listarPersonasPdf;

    @Autowired
    IPersonaService servicio;

    @Operation(summary = "Post para insertar un objeto persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "persona ingresada"),
            @ApiResponse(responseCode = "409", description = "correo ya existe"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
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

    @Operation(summary = "Get para obtener lista de personas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "persona encontrada"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @GetMapping("/listaPersonas")
    public ResponseEntity<Object>listarPersonas(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction
    ){
        try{
            // Definir la ordenación
            Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);

            Pageable pageable = PageRequest.of(page - 1,size, sort);

            Page<Persona> personas = servicio.listarPersonas(pageable);


            if(personas.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La lista esta vacia");
            }else{
                return ResponseEntity.ok().body(personas);
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/listaPersonas/pdf")
    public ResponseEntity<byte[]> generarPdf() {
        try {
            List<Persona> personas = servicio.listarPersonas();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            listarPersonasPdf.generarPdf(personas, outputStream);

            // Convertir el ByteArrayOutputStream a un array de bytes
            byte[] pdfBytes = outputStream.toByteArray();

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Get para buscar un objeto persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "persona ingresada"),
            @ApiResponse(responseCode = "404", description = "persona no encontrada")
    })
    @GetMapping("/buscarPersona")
    public ResponseEntity<Object> buscarPersona(@RequestParam String email) {
        Persona persona = servicio.buscarPersona(email);
        if (persona != null) {
            return ResponseEntity.ok().body(persona);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }

    @Operation(summary = "Delete para eliminar una persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "persona eliminada"),
            @ApiResponse(responseCode = "404", description = "persona no encontrada"),
    })
    @DeleteMapping("/eliminarPersona")
    public ResponseEntity<String> eliminarPersona(@RequestParam String email) {
        boolean eliminado = servicio.eliminarPersona(email);
        if (eliminado) {
            return ResponseEntity.status(HttpStatus.OK).body("Persona eliminada con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar persona");
        }
    }

    @Operation(summary = "Put para modificar una persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "persona editada"),
            @ApiResponse(responseCode = "404", description = "correo no encontrado")
    })
    @PutMapping("/modificarPersona")
    public ResponseEntity<String> modificarPersona(@RequestParam String email, @RequestBody Persona persona){
        boolean editado = servicio.modificarPersona(email, persona);
        if(editado){
            return ResponseEntity.status(HttpStatus.OK).body("Persona modificada con exito");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al editar persona");
        }
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportarPdfPersonas() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("personasReporte", "ReportePersonas.pdf");
        return ResponseEntity.ok().headers(headers).body(servicio.exportarPdf());
    }

}
