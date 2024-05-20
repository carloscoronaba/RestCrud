package com.neoris.dinamita.rest.RestCrud.service;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.FileNotFoundException;
import java.util.List;

public interface IPersonaService {

    public Page<Persona> listarPersonas(Pageable pageable);

    public boolean insertarPersona(Persona persona);

    public boolean eliminarPersona(String email);

    public boolean modificarPersona(String email, Persona persona);

    public Persona buscarPersona(String email);

    List<Persona> listarPersonas();

    public byte[] exportarPdf() throws JRException, FileNotFoundException;
}
