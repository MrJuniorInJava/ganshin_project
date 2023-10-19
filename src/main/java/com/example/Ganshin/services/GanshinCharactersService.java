package com.example.Ganshin.services;

import com.example.Ganshin.models.GanshinCharacter;
import com.example.Ganshin.models.Image;
import com.example.Ganshin.models.Property;
import com.example.Ganshin.repositories.GanshinCharactersRepository;
import com.example.Ganshin.repositories.ImagesRepository;
import com.example.Ganshin.repositories.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class GanshinCharactersService {

    private final GanshinCharactersRepository ganshinCharactersRepository;
    private final ImagesRepository imagesRepository;
    private final PropertiesRepository propertiesRepository;

    @Autowired
    public GanshinCharactersService(GanshinCharactersRepository ganshinCharactersRepository,
                                    ImagesRepository imagesRepository,
                                    PropertiesRepository propertiesRepository) {
        this.ganshinCharactersRepository = ganshinCharactersRepository;
        this.imagesRepository = imagesRepository;
        this.propertiesRepository = propertiesRepository;
    }

    public List<GanshinCharacter> findAll() {
        return ganshinCharactersRepository.findAll();
    }

    public GanshinCharacter findOne(int id) {
        return ganshinCharactersRepository.findById(id).orElse(null);
    }

    @Transactional
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

    @Transactional
    public void edit(GanshinCharacter newCharacter, MultipartFile file, int id) throws IOException {
        Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            imagesRepository.deleteById(ganshinCharactersRepository.findById(id).get()
                    .getImages().get(0).getId());
            newCharacter.addImageToCharacter(image);
        }
        newCharacter.setId(id);
        ganshinCharactersRepository.save(newCharacter);

    }

    @Transactional
    public void delete(int id) {
        ganshinCharactersRepository.deleteById(id);
    }

    @Transactional
    public void addProperty(Property property, int idCharacter) {
        GanshinCharacter character = ganshinCharactersRepository.findById(idCharacter).orElse(null);
        property.setCharacter(character);
        character.addPropertiesToCharacter(property);
        propertiesRepository.save(property);
    }

    //Доп методы, необходимые для методов сервиса
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
