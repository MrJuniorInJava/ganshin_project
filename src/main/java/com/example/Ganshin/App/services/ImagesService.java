package com.example.Ganshin.App.services;

import com.example.Ganshin.App.models.Image;
import com.example.Ganshin.App.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
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
