package com.neoris.dinamita.rest.RestCrud.servicio;

import com.neoris.dinamita.rest.RestCrud.modelo.Persona;

import java.util.List;

public interface IPersonaServicio {

    public List<Persona> listarPersonas();

    public boolean insertarPersona(Persona persona);

    public boolean eliminarPersona(String email);

    public boolean modificarPersona(String email, Persona persona);

    public Persona buscarPersona(String email);

}
