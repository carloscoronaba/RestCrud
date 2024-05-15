package com.neoris.dinamita.rest.RestCrud.repository;

import com.neoris.dinamita.rest.RestCrud.model.VideoJuego;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VideoJuegoRepository extends JpaRepository<VideoJuego, Integer> {

    //Encuentra
    @Query("SELECT v FROM VideoJuego v")
    public Page<VideoJuego> findAll(Pageable pageable);
    //public List<VideoJuego> findAllVideojuegos();

    @Query("SELECT v FROM VideoJuego v WHERE v.idJuego = :id")
    public VideoJuego findVideoJuegosById(@Param("id") Integer id);

    @Query("SELECT v FROM VideoJuego v WHERE v.titulo = :titulo")
    public VideoJuego findVideoJuegosByTitulo(@Param("titulo") String titulo);

    /*@Transactional
    @Modifying
    @Query("INSERT INTO VideoJuego v(v.titulo, v.desarrolladora, v.lanzamiento) VALUES (:titulo, :desarrolladora, :lanzamiento)")
    void insertVideoJuego(@Param("titulo") String titulo, @Param("desarrolladora") String desarrolladora, @Param("lanzamiento") int lanzamiento);
    */

    @Transactional
    @Modifying
    @Query("INSERT INTO VideoJuego v(v.titulo, v.desarrolladora, v.lanzamiento, v.urlPortada) VALUES (:titulo, :desarrolladora, :lanzamiento, :urlPortada)")
    void insertVideoJuego(@Param("titulo") String titulo, @Param("desarrolladora") String desarrolladora, @Param("lanzamiento") int lanzamiento, @Param("urlPortada") String urlPortada);

    @Transactional
    @Modifying
    @Query("DELETE FROM VideoJuego v WHERE v.idJuego = :id")
    void deleteVideoJuegoById(@Param("id") Integer id);

    /*@Transactional
    @Modifying
    @Query("UPDATE VideoJuego v SET v.titulo = :titulo, v.desarrolladora = :desarrolladora, v.lanzamiento = :lanzamiento WHERE v.idJuego = :id")
    void updateVideoJuegoById(@Param("id") Integer id, @Param("titulo") String titulo, @Param("desarrolladora") String desarrolladora, @Param("lanzamiento") int lanzamiento);
    */

    @Transactional
    @Modifying
    @Query("UPDATE VideoJuego v SET v.titulo = :titulo, v.desarrolladora = :desarrolladora, v.lanzamiento = :lanzamiento, v.urlPortada = :urlPortada WHERE v.idJuego = :id")
    void updateVideoJuegoById(@Param("id") Integer id, @Param("titulo") String titulo, @Param("desarrolladora") String desarrolladora, @Param("lanzamiento") int lanzamiento, @Param("urlPortada") String urlPortada);


    @Query("SELECT v FROM VideoJuego v WHERE v.desarrolladora = :desarrolladora ")
    public Page<VideoJuego> findAllVideoJuegosByDesarrolladoraOrderBy(@Param("desarrolladora") String desarrolladora, Pageable pageable);
    //public List<VideoJuego> findAllVideoJuegosByDesarrolladoraOrderBy(@Param("desarrolladora") String desarrolladora);
}
