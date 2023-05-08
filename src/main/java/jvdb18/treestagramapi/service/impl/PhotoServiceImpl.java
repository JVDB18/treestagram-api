package jvdb18.treestagramapi.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

import jvdb18.treestagramapi.model.entities.Photo;
import jvdb18.treestagramapi.repository.PhotoRepository;

@Service
public class PhotoServiceImpl {
    private final PhotoRepository repository;
    private final MongoTemplate mongoTemplate;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFsOperations operations;
    public PhotoServiceImpl(PhotoRepository repository, MongoTemplate mongoTemplate){
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public String addPhoto(String username, String desc, MultipartFile file) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "photo");
        metaData.put("desc", desc);
        ObjectId id = gridFsTemplate.store(file.getInputStream(), username ,file.getContentType(), metaData);
        Photo entity = new Photo();
        entity.setDescription(desc);
        entity.setUserId(username);
        entity.setUrl(id.toString());
        repository.save(entity);
        return entity.getUrl();
    }
    public Photo getPhoto(String id) throws IllegalStateException, IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        Photo photo = new Photo();
        photo.setDescription(file.getMetadata().get("desc").toString());
        photo.setImage(operations.getResource(file).getInputStream());
        photo.setUrl(file.getId().asObjectId().getValue().toString());
        return photo;
    }
    // public List<Photo> getPhotosByUsername(String username) throws IllegalStateException, IOException{
    //    GridFsResource[] ressources = gridFsTemplate.getResources(username+"*");
    //    List<Photo> urls = new ArrayList<>();
    //    for (GridFsResource gridFsResource : ressources) {
    //     Photo photo = new Photo();
    //     photo.setDescription(
    //         gridFsResource.getGridFSFile().getMetadata().get("desc").toString());
    //     photo.setUrl(gridFsResource.getGridFSFile().getId().asObjectId().getValue().toString());
    //     photo.setUserId(username);
    //     urls.add(photo);
    //    }
    //    return urls;
    // }
    public List<String> getPhotosByUsername(String username) throws IllegalStateException, IOException{
       List<String> urls = new ArrayList<String>();
       Query query = new Query();
       query.addCriteria(Criteria.where("userId").is(username));
       List<Photo> pics = mongoTemplate.find(query, Photo.class);
       System.out.println(pics);
       for (Photo pic : pics) {
            urls.add(pic.getUrl());
       }
       return urls;
    }
    public List<String> getAll() throws IllegalStateException, IOException {
        // List<GridFSFile> files = new ArrayList<GridFSFile>();
        // gridFsTemplate.find(new Query()).into(files);
        // List<Photo> photos = new ArrayList<Photo>();
        // for (GridFSFile file : files) {
        //     Photo photo = new Photo();
        //     photo.setDescription(file.getMetadata().get("desc").toString());
        //     photo.setUrl(file.getId().asObjectId().getValue().toString());
        //     photo.setUserId(file.getMetadata().get("username").toString());
        //     photos.add(photo);
        // }

        List<Photo> photos = repository.findAll();
        List<String> urls = new ArrayList<String>();
        for (Photo pic : photos) {
            urls.add(pic.getUrl());
        }
        return urls;
    }
}
