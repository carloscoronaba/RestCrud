package com.neoris.dinamita.rest.RestCrud.service.imp;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import com.neoris.dinamita.rest.RestCrud.model.UserModel;
import com.neoris.dinamita.rest.RestCrud.repository.IUserRepository;
import com.neoris.dinamita.rest.RestCrud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserModel findByName(String user) {
        return userRepository.findByName(user);
    }

    @Override
    public boolean agregarUsuario(UserModel userModel) {
        try {

            UserModel usuarioExistente = userRepository.findUserModelByEmail(userModel.getEmail().toUpperCase());
            System.out.println(usuarioExistente);

            if(usuarioExistente!=null){
                System.out.println("Usuario existente");
                return false;
            }else{
                // Convertir los campos a mayúsculas antes de guardar
                userModel.setName(userModel.getName().toUpperCase());
                userModel.setEmail(userModel.getEmail().toUpperCase());
                // Codificar la contraseña antes de guardarla
                String passwordCodificada = codificarPsw(userModel.getPassword());
                userModel.setPassword(passwordCodificada);
                userRepository.save(userModel);
                return true;
            }
        }catch (Exception ex){
            System.out.println("Erorrrrrrrrrrrrrr");
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(String email) {
        try{
            if(email != "") {
                UserModel userModel = userRepository.findUserModelByEmail(email.toUpperCase());
                userRepository.delete(userModel);
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public List<UserModel> listaUsuarios() {
        try {
            return userRepository.findAll();
        } catch (Exception ex) {
            // Manejar la excepción adecuadamente
            return null;
        }
    }

    @Override
    public boolean editarUsuario(String email, UserModel newUserModel) {
        try{
            if(email != "" && newUserModel !=null){
                UserModel userModel = userRepository.findUserModelByEmail(email.toUpperCase());

                userModel.setName(newUserModel.getName().toUpperCase());
                userModel.setEmail(newUserModel.getEmail().toUpperCase());
                // Codificar la contraseña antes de guardarla
                String passwordCodificada = codificarPsw(newUserModel.getPassword());
                userModel.setPassword(passwordCodificada);
                userRepository.save(userModel);

                return true;
            }
        }catch(Exception ex){
            return false;
        }
        return false;
    }

    private String codificarPsw(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
