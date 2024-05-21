package com.neoris.dinamita.rest.RestCrud.service;

import com.neoris.dinamita.rest.RestCrud.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    public UserModel findByName(String user);

}
