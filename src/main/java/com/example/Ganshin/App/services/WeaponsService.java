package com.example.Ganshin.App.services;

import com.example.Ganshin.App.models.GanshinCharacter;
import com.example.Ganshin.App.models.Weapon;
import com.example.Ganshin.App.repositories.GanshinCharactersRepository;
import com.example.Ganshin.App.repositories.WeaponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WeaponsService {
    private final WeaponsRepository weaponsRepository;
    private final GanshinCharactersRepository charactersRepository;

    @Autowired
    public WeaponsService(WeaponsRepository weaponsRepository, GanshinCharactersRepository charactersRepository) {
        this.weaponsRepository = weaponsRepository;
        this.charactersRepository = charactersRepository;
    }

    public List<Weapon> getAllWeapons() {
        return weaponsRepository.findAll();
    }

    public Weapon findWeaponByName(String weaponName) {
        return weaponsRepository.findByName(weaponName).orElse(null);
    }

    @Transactional
    public void addWeapon(Weapon weapon, int idCharacter) {
        GanshinCharacter character = charactersRepository.findById(idCharacter).orElse(null);
        weapon.addCharacterToWeapon(character);
        weaponsRepository.save(weapon);
    }

    @Transactional
    public void deleteById(int idWeapon) {
        Weapon weapon = weaponsRepository.findById(idWeapon).orElse(null);
        for (GanshinCharacter character : weapon.getCharacters()) {
            character.getWeapons().remove(weapon);
        }
        weaponsRepository.deleteById(idWeapon);
    }
}
