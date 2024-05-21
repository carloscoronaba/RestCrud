package com.neoris.dinamita.rest.RestCrud.controller;

import com.neoris.dinamita.rest.RestCrud.dao.AuthRequestDto;
import com.neoris.dinamita.rest.RestCrud.dao.AuthResponseDto;
import com.neoris.dinamita.rest.RestCrud.jwt.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody AuthRequestDto authRequestDto){

        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String hashedPassword = encoder.encode(authRequestDto.getPassword());
        try {
            //1. Gestion authenticationManager
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequestDto.getUser(), authRequestDto.getPassword()
            ));

            //2. Validar el usuario en la bd
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequestDto.getUser());

            //3. Generar token
            String jwt = this.jwtUtilService.generateToken(userDetails);

            AuthResponseDto authResponseDto = new AuthResponseDto();
            authResponseDto.setToken(jwt);

            return new ResponseEntity<AuthResponseDto>(authResponseDto, HttpStatus.OK);

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error Authetication:::" + e.getMessage());
        }


    }

}
