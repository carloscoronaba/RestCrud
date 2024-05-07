package com.neoris.dinamita.rest.RestCrud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.dinamita.rest.RestCrud.model.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

// Spring test
@SpringBootTest
@WebAppConfiguration
class PersonaControllerTest {

    // URL base para las solicitudes CRUD
    private final static String BASE_URL = "/crud/";

    // MockMvc se utiliza para simular las solicitudes HTTP
    MockMvc mockMvc;

    // Contexto de aplicación web para la configuración del MockMvc
    @Autowired
    private WebApplicationContext webApplicationContext;

    // Configuración antes de cada prueba

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void insertarPersona() throws Exception {
        // Construir una Persona para la prueba
        Persona persona = buildPersona();

        // Realizar la solicitud POST para insertar la Persona
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "insertar")
                        // Especifica que la solicitud acepta JSON como tipo de contenido.
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        //Especifica que el tipo de contenido del cuerpo de la solicitud es JSON.
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        //se establece el contenido del cuerpo
                        .content(mapToJson(persona)))
                //devolver una simulación
                .andReturn();
        // Verificar que la respuesta sea exitosa (código 200)
        assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    void listarPersonas() throws Exception {
        // Realizar la solicitud GET para listar a las Personas
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "listaPersonas")
                        // Especifica que la solicitud acepta JSON como tipo de contenido.
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        //Especifica que el tipo de contenido del cuerpo de la solicitud es JSON.
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                //devolver una simulación
                .andReturn();
        // Verificar que la respuesta sea exitosa (código 200)
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void buscarPersona() throws Exception {
        // Correo electrónico para la prueba
        String email = "carlos@neoris.com";

        // Realizar la solicitud GET para buscar la Persona por correo electrónico
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "buscarPersona")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("email", email))
                .andReturn();

        // Verificar que la respuesta sea exitosa (código 200)
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void eliminarPersonaI() throws Exception {
        // correo electrónico para la prueba
        String email = "carlos@neoris.com";

        // Realizar la solicitud DELETE para eliminar la Persona
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "eliminarPersona")
                        .param("email", email))
                .andReturn();
        // Verificar que la respuesta sea exitosa (código 200)
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void modificarPersonaInexistente() throws Exception {
        Persona persona = buildPersona();
        String email = "carlos@neoris.com";

        // Realizar la solicitud PUT para modificar la Persona
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "modificarPersona")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("email", email)
                        .content(mapToJson(persona)))
                .andReturn();

        // Verificar que la respuesta sea exitosa (código 200)
        assertEquals(404, result.getResponse().getStatus());
    }

    // Método para convertir un objeto a formato JSON
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    // Método para construir una Persona de prueba
    private Persona buildPersona() {
        // Datos de prueba
        Persona persona = new Persona();
        persona.setNombre("Carlos");
        persona.setApellido("Corona");
        persona.setEdad(23);
        persona.setEmail("carlos@neoris.com");
        return persona;
    }
}