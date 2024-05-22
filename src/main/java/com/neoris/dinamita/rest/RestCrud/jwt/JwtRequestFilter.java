package com.neoris.dinamita.rest.RestCrud.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    //Realiza la logica principal del filtro y se llama automaticamente por Spring en cada solicitud de token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization"); //Obtiene el encabezado

        //Verifica si comienza con Bearer
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            //Si es valido extrae el token JWT y el username
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtilService.extractUsername(jwt);

            //Verifica si el nombre de usuario es valido y si no hay autenticacion actualmente
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //Carga los detalles del usuario
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                //Si el token es valido crea un objeto UsernamePassword y establece esta autenticacion en el contexto de seguridad
                if (jwtUtilService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response); //Pasa la solicitud al siguiente filtro de filterChain
    }
}
