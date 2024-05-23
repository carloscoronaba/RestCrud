package com.neoris.dinamita.rest.RestCrud.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MetricsConfig {

    // Bean para proporcionar el registro de métricas Prometheus
    @Bean
    public MeterRegistry meterRegistry() {
        return new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
    }

    // Bean para crear un contador de solicitudes personalizadas
    @Bean
    public Counter customRequestCounter(MeterRegistry meterRegistry) {
        return Counter.builder("custom_requests_total") // Nombre del contador
                .description("Total number of custom requests") // Descripción del contador
                .register(meterRegistry); // Registrando el contador en el registro de métricas
    }
}
