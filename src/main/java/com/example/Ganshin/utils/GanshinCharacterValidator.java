package com.example.Ganshin.utils;

import com.example.Ganshin.models.GanshinCharacter;
import com.example.Ganshin.repositories.GanshinCharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class GanshinCharacterValidator implements Validator {
    private final GanshinCharactersRepository ganshinCharactersRepository;

    @Autowired
    public GanshinCharacterValidator(GanshinCharactersRepository ganshinCharactersRepository) {
        this.ganshinCharactersRepository = ganshinCharactersRepository;
    }



    @Override
    public boolean supports(Class<?> clazz) {
        return GanshinCharacter.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GanshinCharacter character = (GanshinCharacter) target;

        if (ganshinCharactersRepository.findByName(character.getName()).isPresent()) {
            errors.rejectValue("name", "", "Персонаж с таким именем уже добавлен");
        }
    }
}
