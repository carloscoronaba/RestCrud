package com.neoris.dinamita.rest.RestCrud.servicio.imp;

import com.neoris.dinamita.rest.RestCrud.modelo.Persona;
import com.neoris.dinamita.rest.RestCrud.servicio.IPersonaServicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServicioImp implements IPersonaServicio {

    private List<Persona> listaPersonas = new ArrayList<>();;

    Persona persona = new Persona();
    Persona persona2 = new Persona();
    Persona persona3 = new Persona();
    Persona persona4 = new Persona();

    @Override
    public List<Persona> listarPersonas() {

        return listaPersonas;
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

        try{
            for (Persona persona : listaPersonas){
                if (persona.getEmail().equals(email)){
                    return persona;
                }
                else {
                    System.out.println("No existe persona con ese email");
                    return null;
                }
            }

        }catch (Exception ex){
            System.out.println("Error al buscar persona: " + ex.getMessage());

        }
        return null;
    }

}
