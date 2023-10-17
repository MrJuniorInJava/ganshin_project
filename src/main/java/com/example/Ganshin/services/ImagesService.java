package com.example.Ganshin.services;

import com.example.Ganshin.models.Image;
import com.example.Ganshin.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagesService {
    private final ImagesRepository imagesRepository;

    @Autowired
    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    public Image findById(int id) {
        return imagesRepository.findById(id).orElse(null);
    }
}
