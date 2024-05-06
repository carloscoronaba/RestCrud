package com.neoris.dinamita.rest.RestCrud.servicio.imp;

import com.neoris.dinamita.rest.RestCrud.controller.PersonaController;
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
        List<Persona> lista = personaRepositorio.findAll();
        return lista;
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

        try{
            Persona persona = personaRepositorio.findPersonaByEmail(email);
            personaRepositorio.delete(persona);
            return true;
        }catch(Exception ex){
            return false;
        }

    }

    @Override
    public boolean modificarPersona(String email, Persona personaNueva) {
        try{
            Persona personaEditar = personaRepositorio.findPersonaByEmail(email);
            personaEditar.setNombre(personaNueva.getNombre());
            personaEditar.setApellido(personaNueva.getApellido());
            personaEditar.setEdad(personaNueva.getEdad());
            personaEditar.setEmail(personaNueva.getEmail());
            personaRepositorio.save(personaEditar);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public Persona buscarPersona(String email) {
        Persona persona = personaRepositorio.findPersonaByEmail(email);
        return persona;
    }
}
