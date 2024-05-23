package com.neoris.dinamita.rest.RestCrud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.auditing.config.AuditingConfiguration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    // Anotaciones para la auditoría de entidades
    @CreatedBy
    protected String creadoPor; // Campo para almacenar quién creó la entidad

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaHoraCreacion; // Campo para almacenar la fecha y hora de creación de la entidad

    @LastModifiedBy
    protected String ultimaActualizacionPor; // Campo para almacenar quién realizó la última actualización de la entidad

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaHoraUltimaActualizacion; // Campo para almacenar la fecha y hora de la última actualización de la entidad
}
