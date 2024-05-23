package com.neoris.dinamita.rest.RestCrud.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImp implements AuditorAware<String> {
    @Override
    // Método para obtener el auditor actual
    public Optional<String> getCurrentAuditor() {
        // Obtenemos la autenticación actual del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verificamos si la autenticación es nula o no está autenticada
        if(authentication == null || !authentication.isAuthenticated()){
            return null; // Devolvemos null si no hay autenticación válida
        }

        // Devolvemos el nombre del usuario autenticado en mayúsculas como Optional
        return Optional.of(authentication.getName().toUpperCase());

        // También podríamos devolver un usuario de prueba en lugar del usuario autenticado
        // return Optional.of("USUARIO PRUEBA");
    }
}
