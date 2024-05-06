package com.neoris.dinamita.rest.RestCrud.servicio.imp;

import com.neoris.dinamita.rest.RestCrud.modelo.Persona;
import com.neoris.dinamita.rest.RestCrud.repositorio.PersonaRepositorio;
import com.neoris.dinamita.rest.RestCrud.servicio.IPersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServicioImp implements IPersonaServicio {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Override
    public List<Persona> listarPersonas() {
        return List.of();
    }

    @Override
    public boolean insertarPersona(Persona persona) {

        try {
            personaRepositorio.save(persona);
            return true;
        }catch (Exception ex){
            return false;
        }

    }

    @Override
    public boolean eliminarPersona(String email) {
        return false;
    }

    @Override
    public boolean modificarPersona(String email, Persona persona) {
        return false;
    }

    @Override
    public Persona buscarPersona(String email) {
        return null;
    }
}
