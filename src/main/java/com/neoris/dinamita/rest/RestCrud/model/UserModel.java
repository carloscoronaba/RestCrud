package com.neoris.dinamita.rest.RestCrud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserModel {

    @Id
    Integer user_id;

    String name;

    String password;

    String email;


}
