package jvdb18.treestagramapi.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@RestController
public class PhotoController {
    private PhotoServiceImpl photoService;
    public PhotoController(PhotoServiceImpl photoService){
        this.photoService = photoService;
    }
    @PostMapping("/photos/add")
    public String addPhoto(@RequestParam("username") String username, @RequestParam("description") String desc, @RequestParam("image") MultipartFile file, Model model) throws Exception {
        String id = photoService.addPhoto(username, desc, file);
        return "redirect:/photos/"+ id;
    }
    @CrossOrigin
    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable String id, Model model) throws Exception {
        Photo photo = photoService.getPhoto(id);
        return photo;
    }
    @CrossOrigin
    @GetMapping("/photos/stream/{id}")
    public void streamPhoto(@PathVariable String id, HttpServletResponse response) throws Exception {
        Photo photo = photoService.getPhoto(id);
        FileCopyUtils.copy(photo.getImage(), response.getOutputStream());
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