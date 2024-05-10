package com.neoris.dinamita.rest.RestCrud.service.imp;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import com.neoris.dinamita.rest.RestCrud.repository.PersonaRepository;
import com.neoris.dinamita.rest.RestCrud.repository.VideoJuegoRepository;
import com.neoris.dinamita.rest.RestCrud.service.IRelacionPersonaVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
            if(persona != null && videoJuego != null && !persona.getVideojuegos().contains(videoJuego)){
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
            persona.setVideojuegos(videojuegosPersona);
            personaRepository.save(persona);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<VideoJuego> listaVideoJuegosPorPersona(String email) {

        try {
            Persona persona = personaRepository.findPersonaByEmail(email.toUpperCase());

            if (persona != null && !persona.getVideojuegos().isEmpty()) {
                return persona.getVideojuegos();
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}