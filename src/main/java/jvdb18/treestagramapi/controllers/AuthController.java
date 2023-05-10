package jvdb18.treestagramapi.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jvdb18.treestagramapi.form.LoginForm;
import jvdb18.treestagramapi.form.RegistrationForm;
import jvdb18.treestagramapi.jwt.JWTHolderDTO;
import jvdb18.treestagramapi.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController( AuthService authService) {
        this.authService = authService;
    }
    @CrossOrigin
    @PostMapping("/sign_up")
    public void register(@RequestBody @Valid RegistrationForm form){
        authService.register( form );
    }
    @CrossOrigin
    @PostMapping("/sign_in")
    public JWTHolderDTO login(@RequestBody @Valid LoginForm form){
        return authService.login( form );
    }

}

