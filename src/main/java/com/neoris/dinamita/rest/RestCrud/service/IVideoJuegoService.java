package com.neoris.dinamita.rest.RestCrud.service;

import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVideoJuegoService {

    //public List<VideoJuego> listarVideoJuegos();
    public Page<VideoJuego> paginarVideoJuegos(Pageable pageable);

    public boolean insertarVideoJuego(VideoJuego videoJuego);

    public boolean eliminarVideoJuego(Integer id);

    public boolean modificarVideoJuego(Integer id, VideoJuego videoJuego);

    public VideoJuego buscarVideoJuego(Integer id);

    public Page<VideoJuego> listarVideoJuegosPorDesarrolladora(String desarrolladora, Pageable pageable);

}
