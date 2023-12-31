package com.example.Ganshin.App.controllers;

import com.example.Ganshin.App.models.GanshinCharacter;
import com.example.Ganshin.App.services.GanshinCharactersService;
import com.example.Ganshin.App.utils.GanshinCharacterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;

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
        model.addAttribute("role",ganshinCharactersService.getRoleUser());
        return "characters/show_characters";
    }
    @GetMapping("/{id}")
    public String showCharacter(@PathVariable("id") int id, Model model) {
        String statsList= ganshinCharactersService.findOne(id).get().getStats().toString();
        String weaponsList= ganshinCharactersService.findOne(id).get().getWeapons().toString();
        model.addAttribute("character", ganshinCharactersService.findOne(id));
        model.addAttribute("properties", ganshinCharactersService.findOne(id).get().getProperties());
        model.addAttribute("items", ganshinCharactersService.findOne(id).get().getItems());
        model.addAttribute("stats", statsList.substring(1,statsList.length()-1));
        model.addAttribute("role",ganshinCharactersService.getRoleUser());
        model.addAttribute("weapons",weaponsList.substring(1,weaponsList.length()-1));
        return "characters/show_one_character";
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
    @GetMapping("/table")
    public String tablePage(Model model){
        model.addAttribute("characters",ganshinCharactersService.findAll());
        model.addAttribute("role",ganshinCharactersService.getRoleUser());
        return "characters/table";
    }


}
