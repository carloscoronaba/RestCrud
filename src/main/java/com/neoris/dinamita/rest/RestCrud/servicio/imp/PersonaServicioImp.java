package com.neoris.dinamita.rest.RestCrud.servicio.imp;

import com.neoris.dinamita.rest.RestCrud.modelo.Persona;
import com.neoris.dinamita.rest.RestCrud.servicio.IPersonaServicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServicioImp implements IPersonaServicio {

    private List<Persona> listaPersonas;

    @Override
    public List<Persona> listarPersonas() {
        return List.of();
    }

    @Override
    public boolean insertarPersona(Persona persona) {

        try{
            listaPersonas.add(persona);
            return true;
        }catch (Exception ex){
            System.out.println("No se ha podido insertar la Persona: " + ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean eliminarPersona(String email) {
        return false;
    }

    @Override
    public boolean modificarPersona(String email) {
        return false;
    }

    @Override
    public Persona buscarPersona(String email) {
        return null;
    }
}
