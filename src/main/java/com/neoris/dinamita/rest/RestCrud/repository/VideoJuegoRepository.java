package com.neoris.dinamita.rest.RestCrud.repository;

import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoJuegoRepository extends JpaRepository<VideoJuego, Integer> {

    @Query("SELECT v FROM VideoJuego v")
    List<VideoJuego> findAllVideojuegos();

}
