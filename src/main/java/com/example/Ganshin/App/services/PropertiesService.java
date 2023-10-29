package com.example.Ganshin.App.services;

import com.example.Ganshin.App.models.Property;
import com.example.Ganshin.App.repositories.PropertiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PropertiesService {
    private final PropertiesRepository propertiesRepository;

    public PropertiesService(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }


    @Transactional
    public void delete(int idProperty) {
        Property property = propertiesRepository.findById(idProperty).orElse(null);
        property.getCharacter().getProperties().remove(property);
        propertiesRepository.deleteById(idProperty);
    }
}
