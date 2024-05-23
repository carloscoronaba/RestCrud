package com.neoris.dinamita.rest.RestCrud.config;

import com.neoris.dinamita.rest.RestCrud.auditor.AuditorAwareImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditorConfig {

    // Configuración para proporcionar el auditor actual
    @Bean
    public AuditorAware<String> auditorProvider(){
        return new AuditorAwareImp(); // Devuelve una instancia de AuditorAwareImp
    }
}
