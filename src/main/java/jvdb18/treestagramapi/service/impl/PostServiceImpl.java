package jvdb18.treestagramapi.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jvdb18.treestagramapi.model.entities.Post;
import jvdb18.treestagramapi.repository.PostRepository;

@Service
public class PostServiceImpl {
    private final MongoTemplate mongoTemplate;
    private final PostRepository postRepository;
    private final PhotoServiceImpl photoService;
    public PostServiceImpl(MongoTemplate mongoTemplate, PostRepository postRepository,  PhotoServiceImpl photoService){
        this.mongoTemplate = mongoTemplate;
        this.postRepository = postRepository;
        this.photoService = photoService;
    }
    
    public void addPost(String desc, String loca, String username, List<String> tags, MultipartFile file) throws IOException{
        Post post = new Post();
        post.setDescription(desc);
        post.setLocalisation(loca);
        post.setUserName(username);
        post.setTags(tags);
        post.setPictureUrl("http://localhost:8080/photos/stream/"+this.photoService.addPhoto(desc, username, file));
        this.postRepository.save(post);
    }
    // à essayer avec formulaire angular
    // public String addPost(PostForm form, MultipartFile file) throws IOException{
    //     Post post = new Post();
    //     post.setDescription(form.getDescription());
    //     post.setLocalisation(form.getLocalisation());
    //     post.setPictureUrl(
    //         this.photoService.addPhoto(form.getUsername(), form.getDescription(), file));
    //     post.setTags(form.getTags());
    //     post.setUserId(form.getUserId());
    //     post.setUserName(form.getUsername());
    //     this.postRepository.save(post);
    //     return post.getId();

    // }
    

    //Get by ID
    public Post getPostById(String id){
        Post post = this.postRepository.findById(id).orElseThrow();
        return post;
    }

    //Get by Username
    public List<Post> getPostsByUsername(String username){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(username));
        List<Post> posts = mongoTemplate.find(query, Post.class);
        return posts;
    }
    //Get All
    public List<Post> getAllPosts(){
        List<Post> posts = this.postRepository.findAll();
        return posts;
    }

}
