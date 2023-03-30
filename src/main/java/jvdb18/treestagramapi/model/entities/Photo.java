package jvdb18.treestagramapi.model.entities;

import java.io.InputStream;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "photo")
public class Photo {
    @Id
    private String url;
    private String description;
    private InputStream image;
    private String userId;
}
