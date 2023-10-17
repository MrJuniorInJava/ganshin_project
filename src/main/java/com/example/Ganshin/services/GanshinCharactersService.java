package com.example.Ganshin.services;

import com.example.Ganshin.models.GanshinCharacter;
import com.example.Ganshin.models.Image;
import com.example.Ganshin.repositories.GanshinCharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GanshinCharactersService {

    private final GanshinCharactersRepository ganshinCharactersRepository;

    @Autowired
    public GanshinCharactersService(GanshinCharactersRepository ganshinCharactersRepository) {
        this.ganshinCharactersRepository = ganshinCharactersRepository;
    }

    public void save(GanshinCharacter character, MultipartFile file) throws IOException {
        Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            character.addImageToCharacter(image);
        }
        character.setPreviewImageId(character.getImages().get(0).getId());
        ganshinCharactersRepository.save(character);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
