package com.neoris.dinamita.rest.RestCrud.repositorio;

import com.neoris.dinamita.rest.RestCrud.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, String> {

    Persona findPersonaByEmail(String email);



}
