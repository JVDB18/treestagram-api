package jvdb18.treestagramapi.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jvdb18.treestagramapi.form.LoginForm;
import jvdb18.treestagramapi.form.RegistrationForm;
import jvdb18.treestagramapi.jwt.JWTHolderDTO;
import jvdb18.treestagramapi.jwt.JwtProvider;
import jvdb18.treestagramapi.model.entities.User;
import jvdb18.treestagramapi.repository.UserRepository;
import jvdb18.treestagramapi.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder encoder,
            AuthenticationManager authManager,
            JwtProvider jwtProvider
    ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void register(RegistrationForm form) {

        User user = form.toEntity();
        user.setPassword( encoder.encode(user.getPassword()) );

        userRepository.save( user );

    }

    @Override
    public JWTHolderDTO login(LoginForm form) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                form.getUsername(),
                form.getPassword()
        );

        auth = authManager.authenticate( auth );

        String token = jwtProvider.createToken( auth );

        return new JWTHolderDTO( token );

    }
}