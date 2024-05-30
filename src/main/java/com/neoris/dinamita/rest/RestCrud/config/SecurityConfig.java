package com.neoris.dinamita.rest.RestCrud.config;

import com.neoris.dinamita.rest.RestCrud.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    //Se configuran opciones de seguridad utilizando el objeto http
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception{
        http
                .cors(withDefaults()) //Habilita la configuracion de CORS
                .csrf(crf -> crf.disable()) //Deshabilitmos la protección CSRF ya que utiliza tokens en vez de cookies de sesion
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/auth/**").permitAll() //Se permite acceso sin autenticacion
                        .requestMatchers("/crud/**").permitAll()
                        .anyRequest().authenticated() //Cualquier otra peticion necesita autenticacion
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) //Se ejecuta el filtro de JWT
                .sessionManagement((session) -> session //Indica que la autenticacion sera con token
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    //Codificador de contraseñas
    @Bean
    PasswordEncoder passwordEncoder () {
        return  new BCryptPasswordEncoder();
    }

    //Autentica las solicitudes de los clientes
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }

}
