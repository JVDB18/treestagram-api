package jvdb18.treestagramapi.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import jvdb18.treestagramapi.form.PostForm;
import jvdb18.treestagramapi.model.entities.Post;
import jvdb18.treestagramapi.service.impl.PhotoServiceImpl;
import jvdb18.treestagramapi.service.impl.PostServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {
    private PostServiceImpl postServiceImpl;
    public PostController(PostServiceImpl postServiceImpl){
        this.postServiceImpl = postServiceImpl;
    }
    //A voir si elle fonctionne via formulaire angular -> non fonctionnel dans postman
    // @PostMapping("/add")
    // public String addPost(@RequestBody @Valid PostForm form, MultipartFile file){
    //     return this.postServiceImpl.addPost(form, file);
    // }
    @PostMapping("add")
    public void addPost(@RequestParam("description") String desc, @RequestParam("localisation") String loca,@RequestParam("username") String username,@RequestParam("userid") String userid, MultipartFile file) throws IOException{
       this.postServiceImpl.addPost(desc, loca, username, userid, null, file);
    }
    @GetMapping("/{id}")
    public Post getById(@PathVariable("id") String id){
        return this.postServiceImpl.getPostById(id);
    }
    @GetMapping("/user/{username}")
    public List<Post> getPostsByUsername(@PathVariable("username") String username){
        return this.postServiceImpl.getPostsByUsername(username);
    }
    @GetMapping("/all")
    public List<Post> getAll(){
        return this.postServiceImpl.getAllPosts();
    }
}
