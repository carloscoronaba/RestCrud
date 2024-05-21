package com.neoris.dinamita.rest.RestCrud.service;

import com.neoris.dinamita.rest.RestCrud.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    public UserModel findByName(String user);

    boolean agregarUsuario(UserModel userModel);

    boolean eliminarUsuario(String email);

    List<UserModel> listaUsuarios();

    boolean editarUsuario(String email ,UserModel userModel);
}
