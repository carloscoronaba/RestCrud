package com.neoris.dinamita.rest.RestCrud.service;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPersonaService {

    public Page<Persona> listarPersonas(Pageable pageable);

    public boolean insertarPersona(Persona persona);

    public boolean eliminarPersona(String email);

    public boolean modificarPersona(String email, Persona persona);

    public Persona buscarPersona(String email);

}
