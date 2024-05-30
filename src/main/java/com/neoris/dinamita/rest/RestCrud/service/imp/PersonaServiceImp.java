package com.neoris.dinamita.rest.RestCrud.service.imp;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.repository.PersonaRepository;
import com.neoris.dinamita.rest.RestCrud.service.IPersonaService;
import com.neoris.dinamita.rest.RestCrud.util.PersonasReportePdf;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class PersonaServiceImp implements IPersonaService {

    @Autowired
    PersonasReportePdf reportePdf;

    @Autowired
    private PersonaRepository personaRepositorio;

    @Override
    public Page<Persona> listarPersonas(Pageable pageable) {
        try {
            return personaRepositorio.findAll(pageable);
        } catch (Exception ex) {
            // Manejar la excepción adecuadamente
            return null;
        }
    }

    @Override
    @Transactional
    public boolean insertarPersona(Persona persona) {
        try {
            // Verificar si el correo electrónico ya existe en la base de datos
            Persona personaExistente = buscarPersona(persona.getEmail());
            if (personaExistente != null) {
                // El correo electrónico ya está registrado, devuelve false
                return false;
            } else {
                // Llamada al procedimiento almacenado solo para la inserción de datos
                personaRepositorio.insertarPersona(persona.getNombre(), persona.getApellido(), persona.getEdad(), persona.getEmail());
                return true;
            }
        } catch (Exception ex) {
            // Capturar otras excepciones
            ex.printStackTrace();
            return false; // Devolver falso en caso de cualquier otra excepción
        }
    }



    private String codificarPsw(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
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
                //String passwordCodificada = codificarPsw(personaNueva.getPassword());
                //personaEditar.setPassword(passwordCodificada);

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


    @Override
    public List<Persona> listarPersonas() {
        try {
            return personaRepositorio.findAll();
        } catch (Exception ex) {
            // Manejar la excepción adecuadamente
            return null;
        }
    }

    //Genera y exporta el reporte PDF
    @Override
    public byte[] exportarPdf() throws JRException, FileNotFoundException, SQLException {
        return reportePdf.exportPdf();
    }
}
