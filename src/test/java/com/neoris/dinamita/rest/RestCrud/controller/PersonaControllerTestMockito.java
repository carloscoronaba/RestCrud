package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.modelo.Persona;
import com.neoris.dinamita.rest.RestCrud.servicio.IPersonaServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

//Pruebas unitarias
class PersonaControllerTestMockito {

    //Se crea una instancia simulada de esta interfaz
    @Mock
    private IPersonaServicio personaServicio;

    //Inyecta personaServicio dentro de personaController
    @InjectMocks
    private PersonaController personaController;

    //Inicializa los mocks
    @BeforeEach
    void setup(){

        MockitoAnnotations.openMocks(this);
    }

    //--------------Pruebas insertarPersona---------------
    @Test
    void insertarPersonaTrue() {
        //Datos de Prueba
        Persona persona = new Persona("d8e31844dd3549e48a10b1d6890d16b2", "Carlos", "Corona", 23, "carlos@neoris.com");

        //Simular el método insertarPersona del servicio cuando se inserta una persona correctamente
        when(personaServicio.insertarPersona(persona)).thenReturn(true);

        //Simula el método insertarPersona del controlador cuando se inserta una persona correctamente
        ResponseEntity<String> response = personaController.insertarPersona(persona);

        //Compara resultado esperado con el simulado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Persona Ingresada con exito: " + persona, response.getBody());

    }

    @Test
    void insertarPersonaFalse(){
        //Datos de Prueba
        Persona persona = new Persona("d8e31844dd3549e48a10b1d6890d16b2", "Carlos", "Corona", 23, "carlos@neoris.com");

        //Simular el método insertarPersona del servicio cuando no se inserta la persona
        when(personaServicio.insertarPersona(persona)).thenReturn(false);

        //Simula el método insertarPersona del controlador cuando no se inserta una persona
        ResponseEntity<String> response = personaController.insertarPersona(persona);

        //Compara resultado esperado con el simulado
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Correo electronico ya existe: " + persona.getEmail(), response.getBody());
    }

/*    @Test
    void insertarPersonaError(){
        //Datos de Prueba
        Persona persona = new Persona("d8e31844dd3549e48a10b1d6890d16b2", "Carlos", "Corona", 23, "carlos@neoris.com");

        //Simular el comportamiento del servicio
        when(personaServicio.insertarPersona(persona)).thenThrow(new Exception());

        //Simula el comportamiento del controlador
        ResponseEntity<String> response = personaController.insertarPersona(persona);

        //Compara resultado esperado con el simulado
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        //assertEquals("No se ha podido insertar la Persona: ", response.getBody());
    }*/

    //--------------Pruebas listarPersonas---------------

    @Test
    void listarPersonas() {
        //Datos de Prueba
        List<Persona> listaEsperada = new ArrayList<>();
        Persona persona1 = new Persona("d8e31844dd3549e48a10b1d6890d16b2", "Carlos", "Corona", 23, "carlos@neoris.com");
        Persona persona2 = new Persona("f8e31844dd3549e48a10b1d6890d16b8", "Marco", "Aguirre", 25, "marco@neoris.com");
        listaEsperada.add(persona1);
        listaEsperada.add(persona2);

        //Simula el metodo listarPersonas del Servicio regresando la lista de Personas
        when(personaServicio.listarPersonas()).thenReturn(listaEsperada);

        //Simula el metodo listarPersonas del Controlador
        ResponseEntity<Object> response = personaController.listarPersonas();

        //Compara resultado esperado con el simulado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaEsperada, response.getBody());
    }

    //--------------Pruebas buscarPersona---------------
    @Test
    void buscarPersonaExistente() {

        //Datos de prueba
        String email = "marco@neoris.com";
        Persona personaEsperada = new Persona("f8e31844dd3549e48a10b1d6890d16b8", "Marco", "Aguirre", 25, "marco@neoris.com");

        //Simula el metodo buscarPersona del Servicio regresando la persona encontrada
        when(personaServicio.buscarPersona(email)).thenReturn(personaEsperada);

        //Simula el metodo buscarPersona del Controlador
        ResponseEntity<Object> response = personaController.buscarPersona(email);

        //Compara resultado esperado con el simulado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personaEsperada, response.getBody());
    }

    @Test
    void buscarPersonaInexistente() {

        //Datos de prueba
        String email = "marco@neoris.com";

        //Simula el metodo buscarPersona del Servicio regresando null porque no se encontro la persona
        when(personaServicio.buscarPersona(anyString())).thenReturn(null);

        //Simula el metodo buscarPersona del Controlador
        ResponseEntity<Object> response = personaController.buscarPersona(email);

        //Compara resultado esperado con el simulado
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Persona no encontrada", response.getBody());
    }

    //--------------Pruebas eliminarPersona---------------
    @Test
    void eliminarPersonaExistente() {

        //Datos de prueba
        String email = "marco@neoris.com";

        //Simula el metodo eliminarPersona del Servicio regresando true porque se elimino la Persona
        when(personaServicio.eliminarPersona(anyString())).thenReturn(true);

        //Simula el metodo eliminarPersona del Controlador
        ResponseEntity<String> response = personaController.eliminarPersona(email);

        //Compara resultado esperado con el simulado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Persona eliminada con éxito", response.getBody());

    }

    @Test
    void eliminarPersonaInexistente() {

        //Datos de prueba
        String email = "marco@neoris.com";

        //Simula el metodo eliminarPersona del Servicio regresando false porque no se elimino la Persona
        when(personaServicio.eliminarPersona(anyString())).thenReturn(false);

        //Simula el metodo eliminarPersona del Controlador
        ResponseEntity<String> response = personaController.eliminarPersona(email);

        //Compara resultado esperado con el simulado
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Error al eliminar persona", response.getBody());

    }

    //--------------Pruebas modificarPersona---------------
    @Test
    void modificarPersonaExistente() {

        //Datos de prueba
        String email = "marco@neoris.com";
        Persona personaNueva = new Persona("d8e31844dd3549e48a10b1d6890d16b2", "Carlos", "Corona", 23, "carlos@neoris.com");

        //Simula el metodo modificarPersona del Servicio regresando true porque se modifico la Persona
        when(personaServicio.modificarPersona(anyString(), any(Persona.class))).thenReturn(true);

        //Simula el metodo eliminarPersona del Controlador
        ResponseEntity<String> response = personaController.modificarPersona(email, personaNueva);

        //Compara resultado esperado con el simulado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Persona modificada con exito", response.getBody());
    }

    @Test
    void modificarPersonaInexistente() {

        String email = "inexistente@neoris.com";
        Persona personaNueva = new Persona("d8e31844dd3549e48a10b1d6890d16b2", "Carlos", "Corona", 23, "carlos@neoris.com");

        when(personaServicio.modificarPersona(anyString(), any(Persona.class))).thenReturn(false);

        ResponseEntity<String> response = personaController.modificarPersona(email, personaNueva);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Error al editar persona", response.getBody());
    }
}