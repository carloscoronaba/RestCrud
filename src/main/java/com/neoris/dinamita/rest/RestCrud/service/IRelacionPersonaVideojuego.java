package com.neoris.dinamita.rest.RestCrud.service;

import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;

import java.util.List;

public interface IRelacionPersonaVideojuego {
    public boolean asignarPersonaVideojuego(String email, String tituloVideojuego);

    public boolean eliminarVideojuegoPersona(String email, String tituloVideojuego);

    public boolean editarRegistroVideojuegoPersona(String email, String tituloEditar, String tituloNuevo);

    public List<VideoJuego> listaVideoJuegosPorPersona(String email);
}
