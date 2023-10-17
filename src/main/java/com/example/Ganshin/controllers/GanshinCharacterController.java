package com.example.Ganshin.controllers;

import com.example.Ganshin.models.GanshinCharacter;
import com.example.Ganshin.services.GanshinCharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/characters")
public class GanshinCharacterController {
    private final GanshinCharactersService ganshinCharactersService;

    @Autowired
    public GanshinCharacterController(GanshinCharactersService ganshinCharactersService) {
        this.ganshinCharactersService = ganshinCharactersService;
    }

    @GetMapping()
    public String showCharacters(Model model) {
        model.addAttribute("characters",ganshinCharactersService.findAll());
        return "characters/show_characters";
    }

    @GetMapping("/create")
    public String createCharacterPage() {
        return "characters/create";
    }

    @PostMapping("/create")
    public String createCharacter(@RequestParam("file1") MultipartFile file, GanshinCharacter character) throws IOException {
        ganshinCharactersService.save(character, file);
        return "redirect:/characters";
    }

}
