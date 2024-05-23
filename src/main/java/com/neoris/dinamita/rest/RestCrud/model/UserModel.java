package com.neoris.dinamita.rest.RestCrud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserModel extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer user_id; // Identificador único del usuario

    String name; // Nombre del usuario

    String password; // Contraseña del usuario

    @Column(name = "EMAIL", unique = true)
    String email; // Correo electrónico del usuario
}
