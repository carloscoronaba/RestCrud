package com.neoris.dinamita.rest.RestCrud.service;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;

import java.util.List;

public interface IVideoJuegoService {

    public List<VideoJuego> listarVideoJuego();

    public boolean insertarVideoJuego(VideoJuego videoJuego);

    public boolean eliminarVideoJuego(Integer id);

    public boolean modificarVideoJuego(Integer id, VideoJuego videoJuego);

    public Persona buscarVideoJuego(Integer id);

}
