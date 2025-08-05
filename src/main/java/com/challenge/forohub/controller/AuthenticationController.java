package com.challenge.forohub.controller;

import com.challenge.forohub.domain.user.User;
import com.challenge.forohub.domain.user.UserAuthenticationDTO;
import com.challenge.forohub.infra.security.DataJWTTokenDTO;
import com.challenge.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity userLogin(@RequestBody @Valid @NotNull UserAuthenticationDTO userAuthenticationDTO){
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(
                    userAuthenticationDTO.login(),
                    userAuthenticationDTO.password()
            );
            var authenticatedUser = authenticationManager.authenticate(authenticationToken);
            System.out.println(authenticatedUser.getPrincipal());
            var JWTToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());

            return ResponseEntity.ok(new DataJWTTokenDTO(JWTToken));
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales invalidas");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error procesando la solicitud");
        }
    }
}
