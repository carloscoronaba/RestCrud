package com.neoris.dinamita.rest.RestCrud.service;

import com.neoris.dinamita.rest.RestCrud.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    public UserModel findByName(String user);

    boolean agregarUsuario(UserModel userModel);

    boolean eliminarUsuario(String user);

    List<UserModel> listaUsuarios();

    boolean editarUsuario(String user ,UserModel userModel);
}
