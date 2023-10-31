package com.example.Ganshin.App.services;

import com.example.Ganshin.App.models.GanshinCharacter;
import com.example.Ganshin.App.models.Image;
import com.example.Ganshin.App.repositories.GanshinCharactersRepository;
import com.example.Ganshin.App.repositories.ImagesRepository;
import com.example.Ganshin.App.repositories.ItemsRepository;
import com.example.Ganshin.App.repositories.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GanshinCharactersService {

    private final GanshinCharactersRepository ganshinCharactersRepository;
    private final ImagesRepository imagesRepository;
    private final PropertiesRepository propertiesRepository;
    private final ItemsRepository itemsRepository;

    @Autowired
    public GanshinCharactersService(GanshinCharactersRepository ganshinCharactersRepository,
                                    ImagesRepository imagesRepository,
                                    PropertiesRepository propertiesRepository, ItemsRepository itemsRepository) {
        this.ganshinCharactersRepository = ganshinCharactersRepository;
        this.imagesRepository = imagesRepository;
        this.propertiesRepository = propertiesRepository;
        this.itemsRepository = itemsRepository;
    }

    public List<GanshinCharacter> findAll() {
        return ganshinCharactersRepository.findAll();
    }

    public Optional <GanshinCharacter> findOne(int id) {
        return ganshinCharactersRepository.findById(id);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(GanshinCharacter character, MultipartFile file) throws IOException {
        Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            character.addImageToCharacter(image);
            character.setPreviewImageId(character.getImages().get(0).getId());
        }
        ganshinCharactersRepository.save(character);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void edit(GanshinCharacter newCharacter, MultipartFile file, int id) throws IOException {
        Image image;
        GanshinCharacter ganshinCharacter = ganshinCharactersRepository.findById(id).get();
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            if (!ganshinCharacter.getImages().isEmpty()){
                imagesRepository.deleteById(ganshinCharacter
                        .getImages().get(0).getId());
            }
            newCharacter.addImageToCharacter(image);
        }
        newCharacter.setId(id);
        ganshinCharactersRepository.save(newCharacter);

    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        ganshinCharactersRepository.deleteById(id);
    }


    @Transactional
    public List<GanshinCharacter> searchByName(String name){
        return ganshinCharactersRepository.findByNameStartingWith(name);
    }

    //Доп методы, необходимые для методов сервиса
    public String getRoleUser(){
        List<SimpleGrantedAuthority> grantedAuthorities = (List<SimpleGrantedAuthority>) SecurityContextHolder.
                getContext().getAuthentication().getAuthorities();// Получаем роль пользователя, который находится на странице
        return grantedAuthorities.get(0).toString();
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
