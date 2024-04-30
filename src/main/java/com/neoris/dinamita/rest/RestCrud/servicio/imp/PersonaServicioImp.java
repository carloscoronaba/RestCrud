package com.neoris.dinamita.rest.RestCrud.servicio.imp;

import com.neoris.dinamita.rest.RestCrud.modelo.Persona;
import com.neoris.dinamita.rest.RestCrud.servicio.IPersonaServicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServicioImp implements IPersonaServicio {

    private List<Persona> listaPersonas = new ArrayList<>();;

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
        String personaId = buscarPersona(email).getId();
        try{
            for(Persona persona: listaPersonas){
                if(personaId.equals(persona.getId())){
                    listaPersonas.remove(persona);
                    System.out.println("Persona eliminada con exito.");
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar persona: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificarPersona(String email, Persona personaModificada) {
        String personaId = buscarPersona(email).getId();
        int aux;
        try{
            for(Persona persona: listaPersonas){
                if(personaId.equals(persona.getId())){
                    aux = listaPersonas.indexOf(persona);
                    listaPersonas.set(aux,personaModificada);
                    System.out.println("Persona editada con exito.");
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al editar persona: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Persona buscarPersona(String email) {
        try{
            for (Persona persona : listaPersonas){
                if (persona.getEmail().equals(email)){
                    return persona;
                }
            }
            System.out.println("No existe persona con ese email");
        }catch (Exception ex){
            System.out.println("Error al buscar persona: " + ex.getMessage());

        }
        return null;
    }

}
