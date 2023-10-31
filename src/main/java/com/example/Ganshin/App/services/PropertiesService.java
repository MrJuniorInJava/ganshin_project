package com.example.Ganshin.App.services;

import com.example.Ganshin.App.models.GanshinCharacter;
import com.example.Ganshin.App.models.Property;
import com.example.Ganshin.App.repositories.GanshinCharactersRepository;
import com.example.Ganshin.App.repositories.PropertiesRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PropertiesService {
    private final PropertiesRepository propertiesRepository;
    private final GanshinCharactersRepository charactersRepository;

    public PropertiesService(PropertiesRepository propertiesRepository, GanshinCharactersRepository charactersRepository) {
        this.propertiesRepository = propertiesRepository;
        this.charactersRepository = charactersRepository;
    }


    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int idProperty) {
        Property property = propertiesRepository.findById(idProperty).orElse(null);
        property.getCharacter().getProperties().remove(property);
        propertiesRepository.deleteById(idProperty);
    }
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addProperty(Property property, int idCharacter) {
        GanshinCharacter character = charactersRepository.findById(idCharacter).orElse(null);
        character.addPropertyToCharacter(property);
        propertiesRepository.save(property);
    }
}
