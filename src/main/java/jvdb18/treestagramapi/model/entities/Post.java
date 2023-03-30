package jvdb18.treestagramapi.model.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Document(collection = "post")
public class Post {
    @Id
    private String id;
    //@NotNull
    private String userId;
    @NotNull
    private String userName;
    private String localisation;
    private String description;
    private List<String> tags;
    private String pictureUrl;
}
