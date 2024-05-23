package com.neoris.dinamita.rest.RestCrud.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricasController {

    private final Counter customRequestCounter;

    @Autowired
    public MetricasController(Counter customRequestCounter) {
        this.customRequestCounter = customRequestCounter;
    }

    @GetMapping("/contador")
    public String myAction() {

        // Crea un JSON que incluya el mensaje y las métricas
        String jsonResponse = "{"
                + "\"Total de peticiones\": " + customRequestCounter.count()
                + "}";

        // Retorna el JSON como respuesta
        return jsonResponse;
    }
}