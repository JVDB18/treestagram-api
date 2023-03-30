package jvdb18.treestagramapi.form;

import java.util.List;

import lombok.Data;

@Data
public class PostForm {
    private String userId;
    private String username;
    private String localisation;
    private String description;
    private List<String> tags;
    private String pictureUrl;
}
