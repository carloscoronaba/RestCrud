package com.neoris.dinamita.rest.RestCrud.service;

public interface IRelacionPersonaVideojuego {
    public boolean asignarPersonaVideojuego(String email, String tituloVideojuego);

    public boolean eliminarVideojuegoPersona(String email, String tituloVideojuego);

    public boolean editarRegistroVideojuegoPersona(String email, String tituloEditar, String tituloNuevo);
}
