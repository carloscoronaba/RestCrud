package com.neoris.dinamita.rest.RestCrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/holamundo")
public class HolaMundo {

    @GetMapping("/saludo")
    public void saludar(@RequestParam String saludo) {
        System.out.println("Hola " + saludo);
    }
}
