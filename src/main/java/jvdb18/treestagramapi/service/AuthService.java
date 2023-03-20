package jvdb18.treestagramapi.service;

import jvdb18.treestagramapi.form.LoginForm;
import jvdb18.treestagramapi.form.RegistrationForm;
import jvdb18.treestagramapi.jwt.JWTHolderDTO;

public interface AuthService {

    void register( RegistrationForm form );

    JWTHolderDTO login(LoginForm form );

}