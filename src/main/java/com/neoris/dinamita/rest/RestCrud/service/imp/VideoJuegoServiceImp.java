package com.neoris.dinamita.rest.RestCrud.service.imp;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import com.neoris.dinamita.rest.RestCrud.repository.VideoJuegoRepository;
import com.neoris.dinamita.rest.RestCrud.service.IVideoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoJuegoServiceImp implements IVideoJuegoService {

    @Autowired
    VideoJuegoRepository videoJuegoRepository;

    /*@Override
    public List<VideoJuego> listarVideoJuegos() {
        List<VideoJuego> lista = List.of();
        try{
            lista = videoJuegoRepository.findAllVideojuegos();
            System.out.println(lista);
            return lista;
        }catch (Exception ex){
            return lista;
        }

    }*/

    @Override
    public Page<VideoJuego> paginarVideoJuegos(Pageable pageable){
        Page<VideoJuego> pagina = Page.empty();
        try{
            pagina = videoJuegoRepository.findAll(pageable);
            System.out.println(pagina);
            return pagina;
        }catch (Exception ex){
            return pagina;
        }
    }


    @Override
    public boolean insertarVideoJuego(VideoJuego videoJuego) {
        try {
            VideoJuego juegoExistente = buscarVideoJuegoPorTitulo(videoJuego.getTitulo());

            if(juegoExistente!=null){
                return false;
            }else{
                videoJuegoRepository.insertVideoJuego(videoJuego.getTitulo().toUpperCase(), videoJuego.getDesarrolladora().toUpperCase(), videoJuego.getLanzamiento(), videoJuego.getUrlPortada());
                return true;
            }
        }catch (Exception ex){
            return false;
        }

    }

    @Override
    public boolean eliminarVideoJuego(Integer id) {
        try{
            if(id != null) {
                VideoJuego videoJuego = videoJuegoRepository.findVideoJuegosById(id);
                videoJuegoRepository.deleteVideoJuegoById(videoJuego.getIdJuego());
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean modificarVideoJuego(Integer id, VideoJuego videoJuegoNuevo) {
        try{
            VideoJuego videoJuego = videoJuegoRepository.findVideoJuegosById(id);
            if(videoJuegoNuevo !=null && videoJuego.getIdJuego() != null){
                videoJuegoRepository.updateVideoJuegoById(id,videoJuegoNuevo.getTitulo().toUpperCase(),videoJuegoNuevo.getDesarrolladora().toUpperCase(), videoJuegoNuevo.getLanzamiento(), videoJuego.getUrlPortada());
                return true;
            }
        }catch(Exception ex){
            return false;
        }
        return false;
    }

    @Override
    public VideoJuego buscarVideoJuego(Integer id) {
        try{
            if(id != null ){
                VideoJuego juegoEncontrado = videoJuegoRepository.findVideoJuegosById(id);
                return juegoEncontrado;
            }
        }catch (Exception ex){
            return null;
        }
        return null;
    }

    @Override
    public Page<VideoJuego> listarVideoJuegosPorDesarrolladora(String desarrolladora, Pageable pageable) {
        Page<VideoJuego> pagina = Page.empty();
        //List<VideoJuego> lista = List.of();
        try{
            pagina = videoJuegoRepository.findAllVideoJuegosByDesarrolladoraOrderBy(desarrolladora.toUpperCase(), pageable);
            System.out.println(pagina);
            return pagina;
        }catch (Exception ex){
            return pagina;
        }
    }

    public VideoJuego buscarVideoJuegoPorTitulo(String titulo){

        try{
            if(titulo != null ){
                VideoJuego juegoEncontrado = videoJuegoRepository.findVideoJuegosByTitulo(titulo.toUpperCase());
                return juegoEncontrado;
            }
        }catch (Exception ex){
            return null;
        }
        return null;

    }


}
