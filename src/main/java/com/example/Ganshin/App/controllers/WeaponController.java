package com.example.Ganshin.App.controllers;

import com.example.Ganshin.App.models.Weapon;
import com.example.Ganshin.App.services.WeaponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weapons")
public class WeaponController {
    private final WeaponsService weaponsService;

    @Autowired
    public WeaponController(WeaponsService weaponsService) {
        this.weaponsService = weaponsService;
    }

    @PostMapping("/add/{idCharacter}")
    public String addWeapon(@PathVariable("idCharacter") int idCharacter,
                            @ModelAttribute("weapon") Weapon weapon) {
        weaponsService.addWeapon(weapon,idCharacter);
        return "redirect:/characters/{idCharacter}";
    }
    @PostMapping("/delete/{idCharacter}/{idWeapon}")
    public String deleteItem(@PathVariable("idCharacter") int idCharacter, @PathVariable("idWeapon") int idWeapon) {
        weaponsService.deleteById(idWeapon);
        return "redirect:/characters/{idCharacter}";
    }
}
