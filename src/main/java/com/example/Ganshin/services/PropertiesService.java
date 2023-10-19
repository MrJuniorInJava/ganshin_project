package com.example.Ganshin.services;

import com.example.Ganshin.models.Property;
import com.example.Ganshin.repositories.PropertiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PropertiesService {
    private final PropertiesRepository propertiesRepository;
    private final GanshinCharactersService ganshinCharactersService;

    public PropertiesService(PropertiesRepository propertiesRepository, GanshinCharactersService ganshinCharactersService) {
        this.propertiesRepository = propertiesRepository;
        this.ganshinCharactersService = ganshinCharactersService;
    }
    @Transactional
    public void save(Property property, int idCharacter){
        property.setCharacter(ganshinCharactersService.findOne(idCharacter));
        ganshinCharactersService.findOne(idCharacter).addPropertiesToCharacter(property);
        propertiesRepository.save(property);
    }
}
