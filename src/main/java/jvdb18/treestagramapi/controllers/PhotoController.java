package jvdb18.treestagramapi.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import jvdb18.treestagramapi.model.entities.Photo;
import jvdb18.treestagramapi.service.impl.PhotoServiceImpl;

@CrossOrigin
@RestController
public class PhotoController {
    private PhotoServiceImpl photoService;
    public PhotoController(PhotoServiceImpl photoService){
        this.photoService = photoService;
    }
    @CrossOrigin
    @PostMapping("/photos/add")
    public void addPhoto(@RequestParam("username") String username, @RequestParam("description") String desc, @RequestParam("image") MultipartFile file, Model model) throws Exception {
       photoService.addPhoto(username, desc, file);
    }
    
    @CrossOrigin
    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable String id, Model model) throws Exception {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("url", "/photos/stream/" + id);
        return photo;
    }

    @CrossOrigin
    @GetMapping("/photos/stream/{id}")
    public ResponseEntity<String> streamPhoto(@PathVariable String id, HttpServletResponse response) throws Exception {
        Photo photo = photoService.getPhoto(id);
        FileCopyUtils.copy(photo.getImage(), response.getOutputStream());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("content-type", "image/jpg");
        return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
    }
    @CrossOrigin
    @GetMapping("/photos/all")
    public List<String> getAllUrl(Model model) throws Exception {
        List<String> urls = photoService.getAll();
        return urls;
    }
    @CrossOrigin
    @GetMapping("/photos/user/{username}")
    public List<String> getUrlsByUsername(@PathVariable String username, Model model) throws Exception {
        List<String> urls= photoService.getPhotosByUsername(username);
        return urls;
    }
}