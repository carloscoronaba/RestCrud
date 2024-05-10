package com.neoris.dinamita.rest.RestCrud.repository;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {

    //Persona findPersonaByEmail(String email);

    @Query("SELECT p FROM Persona p WHERE p.email = :email")
    public Persona findPersonaByEmail(@Param("email") String email);


}
