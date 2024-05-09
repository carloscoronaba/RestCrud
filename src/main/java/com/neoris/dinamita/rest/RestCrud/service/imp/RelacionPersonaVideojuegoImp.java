package com.neoris.dinamita.rest.RestCrud.service.imp;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import com.neoris.dinamita.rest.RestCrud.repository.PersonaRepository;
import com.neoris.dinamita.rest.RestCrud.repository.VideoJuegoRepository;
import com.neoris.dinamita.rest.RestCrud.service.IRelacionPersonaVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelacionPersonaVideojuegoImp implements IRelacionPersonaVideojuego {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    VideoJuegoRepository videoJuegoRepository;


    @Override
    public boolean asignarPersonaVideojuego(String email, String tituloVideojuego) {
        Persona persona = personaRepository.findPersonaByEmail(email.toUpperCase());
        VideoJuego videoJuego = videoJuegoRepository.findVideoJuegosByTitulo(tituloVideojuego.toUpperCase());

        try{
            if(persona != null && videoJuego != null){
                persona.getVideojuegos().add(videoJuego);
                personaRepository.save(persona);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean eliminarVideojuegoPersona(String email, String tituloVideojuego) {
        Persona persona = personaRepository.findPersonaByEmail(email.toUpperCase());
        VideoJuego videoJuego = videoJuegoRepository.findVideoJuegosByTitulo(tituloVideojuego.toUpperCase());

        try {
            if(persona != null && videoJuego != null){
                persona.getVideojuegos().remove(videoJuego);
                personaRepository.save(persona);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean editarRegistroVideojuegoPersona(String email, String tituloEditar, String tituloNuevo) {
        Persona persona = personaRepository.findPersonaByEmail(email.toUpperCase());
        VideoJuego videoJuegoEditar = videoJuegoRepository.findVideoJuegosByTitulo(tituloEditar.toUpperCase());
        VideoJuego videoJuegoNuevo = videoJuegoRepository.findVideoJuegosByTitulo(tituloNuevo.toUpperCase());

        List<VideoJuego> videojuegosPersona = persona.getVideojuegos();

        if(videojuegosPersona.contains(videoJuegoEditar)){
            videojuegosPersona.remove(videoJuegoEditar);
            videojuegosPersona.add(videoJuegoNuevo);
            //videojuegosPersona.set(videoJuegoEditar, videoJuegoNuevo);
            persona.setVideojuegos(videojuegosPersona);
            personaRepository.save(persona);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<VideoJuego> listaVideoJuegosPorPersona(String email) {

        Persona persona = personaRepository.findPersonaByEmail(email.toUpperCase());
        if(persona != null){
            return persona.getVideojuegos();
        }
        return null;
    }
}