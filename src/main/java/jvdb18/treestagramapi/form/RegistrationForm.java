package jvdb18.treestagramapi.form;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jvdb18.treestagramapi.model.entities.User;
import lombok.Data;

import java.util.Set;

@Data
public class RegistrationForm {

    @NotNull
    private String username;
    @NotNull
    @Size(min = 4)
    private String password;

    public User toEntity(){

        User user = new User();
        user.setUsername( username );
        user.setPassword( password );
        user.setRoles( Set.of("USER") );
        return user;

    }

}
