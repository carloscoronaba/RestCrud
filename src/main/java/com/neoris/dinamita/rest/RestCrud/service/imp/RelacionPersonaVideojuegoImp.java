package com.neoris.dinamita.rest.RestCrud.service.imp;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import com.neoris.dinamita.rest.RestCrud.repository.PersonaRepository;
import com.neoris.dinamita.rest.RestCrud.repository.VideoJuegoRepository;
import com.neoris.dinamita.rest.RestCrud.service.IRelacionPersonaVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelacionPersonaVideojuegoImp implements IRelacionPersonaVideojuego {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    VideoJuegoRepository videoJuegoRepository;


    @Override
    public boolean asignarPersonaVideojuego(String email, String tituloVideojuego) {
        Persona persona = personaRepository.findPersonaByEmail(email);
        VideoJuego videoJuego = videoJuegoRepository.findVideoJuegosByTitulo(tituloVideojuego);

        persona.getVideojuegos().add(videoJuego);
        personaRepository.save(persona);
        return true;
    }
}
