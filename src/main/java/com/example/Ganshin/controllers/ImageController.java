package com.example.Ganshin.controllers;

import com.example.Ganshin.models.Image;
import com.example.Ganshin.services.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class ImageController {
    private final ImagesService imagesService;

    @Autowired
    public ImageController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }


    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable int id){
        Image image = imagesService.findById(id);

        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

}
