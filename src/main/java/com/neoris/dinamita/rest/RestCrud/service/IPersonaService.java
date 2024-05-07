package com.neoris.dinamita.rest.RestCrud.service;

import com.neoris.dinamita.rest.RestCrud.model.Persona;

import java.util.List;

public interface IPersonaService {

    public List<Persona> listarPersonas();

    public boolean insertarPersona(Persona persona);

    public boolean eliminarPersona(String email);

    public boolean modificarPersona(String email, Persona persona);

    public Persona buscarPersona(String email);

}
