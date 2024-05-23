package com.neoris.dinamita.rest.RestCrud.service.imp;

import com.neoris.dinamita.rest.RestCrud.model.UserModel;
import com.neoris.dinamita.rest.RestCrud.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    //Carga los detalles del usuario a traves del username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Consulta a la base de datos
        UserModel userModel = this.userRepository.findByName(username.toUpperCase());
        if (username == null){
            throw new UsernameNotFoundException(username.toUpperCase());
        }
        return new User(userModel.getName(), userModel.getPassword(), new ArrayList<>());

    }

}
