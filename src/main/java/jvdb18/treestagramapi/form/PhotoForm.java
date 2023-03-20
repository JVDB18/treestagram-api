package jvdb18.treestagramapi.form;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PhotoForm {
    @NotNull
    private String username;
    @NotNull
    private String description;
    @NotNull
    private MultipartFile image;
}
