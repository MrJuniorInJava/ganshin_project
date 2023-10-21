package com.example.Ganshin.controllers;

import com.example.Ganshin.models.GanshinCharacter;
import com.example.Ganshin.models.Property;
import com.example.Ganshin.services.GanshinCharactersService;
import com.example.Ganshin.utils.GanshinCharacterValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/characters")
public class GanshinCharacterController {
    private final GanshinCharacterValidator ganshinCharacterValidator;
    private final GanshinCharactersService ganshinCharactersService;

    @Autowired
    public GanshinCharacterController(GanshinCharacterValidator ganshinCharacterValidator, GanshinCharactersService ganshinCharactersService) {
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

    @PostMapping("/{id}")
    public String addProperties(@PathVariable("id") int id, @ModelAttribute("properties") Property property) throws IOException {
        ganshinCharactersService.addProperty(property, id);
        return "redirect:/characters/{id}";
    }


    @GetMapping("/create")
    public String createCharacterPage(Model model) {
        model.addAttribute("character", new GanshinCharacter());
        return "characters/create";
    }

    @PostMapping("/create")
    public String createCharacter(@RequestParam("file1") MultipartFile file,
                                  @ModelAttribute("character") @Valid GanshinCharacter character,
                                  BindingResult bindingResult) throws IOException {
        ganshinCharacterValidator.validate(character, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/characters/create";
        }
        ganshinCharactersService.save(character, file);
        return "redirect:/characters";
    }

    @GetMapping("/edit/{id}")
    public String editCharacterPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("character", ganshinCharactersService.findOne(id));
        return "characters/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCharacter(@RequestParam("file1") MultipartFile file,
                                @PathVariable("id") int id,
                                @ModelAttribute("character") @Valid GanshinCharacter character,
                                BindingResult bindingResult) throws IOException {

        ganshinCharacterValidator.validate(character, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/characters/edit";
        }
        ganshinCharactersService.edit(character, file, id);

        return "redirect:/characters/{id}";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        ganshinCharactersService.delete(id);
        return "redirect:/characters";
    }

}
