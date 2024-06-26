package com.neoris.dinamita.rest.RestCrud.repository;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {

    //Persona findPersonaByEmail(String email);

    @Query("SELECT p FROM Persona p WHERE p.email = :email")
    public Persona findPersonaByEmail(@Param("email") String email);

    Page<Persona> findAll(Pageable pageable);

    @Procedure(name = "Persona.insertarPersona")
    void insertarPersona(String nombre, String apellido, int edad, String email);


}
