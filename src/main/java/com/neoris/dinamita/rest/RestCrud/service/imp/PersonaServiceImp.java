package com.neoris.dinamita.rest.RestCrud.service.imp;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.repository.PersonaRepository;
import com.neoris.dinamita.rest.RestCrud.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImp implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepositorio;

    @Override
    public List<Persona> listarPersonas() {
        List<Persona> lista = List.of();
        try {
            lista = personaRepositorio.findAll();
            return lista;
        }catch (Exception ex){
            return lista;
        }

    }

    @Override
    public boolean insertarPersona(Persona persona) {
        try {
            Persona personaExistente = buscarPersona(persona.getEmail());

            if(personaExistente!=null){
                return false;
            }else{
                // Convertir los campos a mayúsculas antes de guardar
                persona.setNombre(persona.getNombre().toUpperCase());
                persona.setApellido(persona.getApellido().toUpperCase());
                persona.setEmail(persona.getEmail().toUpperCase());
                personaRepositorio.save(persona);
                return true;
            }
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean eliminarPersona(String email) {

        try{
            if(email != "") {
                Persona persona = personaRepositorio.findPersonaByEmail(email.toUpperCase());
                personaRepositorio.delete(persona);
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean modificarPersona(String email, Persona personaNueva) {
        try{
            if(email != "" && personaNueva !=null){
                Persona personaEditar = buscarPersona(email);
                personaEditar.setNombre(personaNueva.getNombre().toUpperCase());
                personaEditar.setApellido(personaNueva.getApellido().toUpperCase());
                personaEditar.setEdad(personaNueva.getEdad());
                personaEditar.setEmail(personaNueva.getEmail().toUpperCase());
                personaRepositorio.save(personaEditar);
                return true;
            }
        }catch(Exception ex){
            return false;
        }
        return false;
    }

    @Override
    public Persona buscarPersona(String email) {
        try{
            if(email != ""){
                Persona persona = personaRepositorio.findPersonaByEmail(email.toUpperCase());
                return persona;
            }
        }catch (Exception ex){
            return null;
        }
        return null;
    }
}
