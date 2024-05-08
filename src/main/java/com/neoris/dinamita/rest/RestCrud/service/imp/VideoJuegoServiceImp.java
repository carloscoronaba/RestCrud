package com.neoris.dinamita.rest.RestCrud.service.imp;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import com.neoris.dinamita.rest.RestCrud.repository.VideoJuegoRepository;
import com.neoris.dinamita.rest.RestCrud.service.IVideoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoJuegoServiceImp implements IVideoJuegoService {

    @Autowired
    VideoJuegoRepository videoJuegoRepository;

    @Override
    public List<VideoJuego> listarVideoJuego() {
        List<VideoJuego> lista = List.of();
        try{
            lista = videoJuegoRepository.findAllVideojuegos();
            System.out.println(lista);
            return lista;
        }catch (Exception ex){
            return lista;
        }

    }

    @Override
    public boolean insertarVideoJuego(VideoJuego videoJuego) {

        videoJuegoRepository.save(videoJuego);
        return true;
    }

    @Override
    public boolean eliminarVideoJuego(Integer id) {
        return false;
    }

    @Override
    public boolean modificarVideoJuego(Integer id, VideoJuego videoJuego) {
        return false;
    }

    @Override
    public Persona buscarVideoJuego(Integer id) {
        return null;
    }


}
