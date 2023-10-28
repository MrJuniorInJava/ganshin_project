package com.example.Ganshin.App.controllers;

import com.example.Ganshin.App.models.GanshinCharacter;
import com.example.Ganshin.App.services.GanshinCharactersService;
import com.example.Ganshin.App.utils.GanshinCharacterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/characters")
public class GanshinCharacterControllerForUser {
    private final GanshinCharacterValidator ganshinCharacterValidator;
    private final GanshinCharactersService ganshinCharactersService;

    @Autowired
    public GanshinCharacterControllerForUser(GanshinCharacterValidator ganshinCharacterValidator, GanshinCharactersService ganshinCharactersService) {
        this.ganshinCharacterValidator = ganshinCharacterValidator;
        this.ganshinCharactersService = ganshinCharactersService;

    }

    @GetMapping()
    public String showCharacters(Model model) {
        model.addAttribute("characters", ganshinCharactersService.findAll());
        return "characters/show_characters";
    }

    @GetMapping("/{id}")
    public String showCharacter(@PathVariable("id") int id, Model model,
                                @ModelAttribute("character") GanshinCharacter character) {
        model.addAttribute("character", ganshinCharactersService.findOne(id));
        model.addAttribute("properties", ganshinCharactersService.findOne(id).get().getProperties());
        return "characters/show_one_character";
    }

    @GetMapping("/search")
    public String searchPage(Model model) {
        model.addAttribute("characters", Collections.emptyList());
        return "characters/search";
    }

    @PostMapping("/search")
    public String searchBookByName(@RequestParam(value = "name", required = false) String name,
                                   Model model) {
        model.addAttribute("characters", ganshinCharactersService.searchByName(name));
        return "characters/search";
    }

}
