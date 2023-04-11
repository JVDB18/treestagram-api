package jvdb18.treestagramapi.utils;

import java.time.Instant;

public class ErrorDTO {

        String message;
        Instant time = Instant.now();
    
        public ErrorDTO(String message){
            super();
            this.message = message;
            
        }
}
