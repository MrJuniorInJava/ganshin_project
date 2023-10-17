package com.example.Ganshin.controllers;

import com.example.Ganshin.models.GanshinCharacter;
import com.example.Ganshin.services.GanshinCharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/characters")
public class GanshinCharacterController {
    private final GanshinCharactersService ganshinCharactersService;

    @Autowired
    public GanshinCharacterController(GanshinCharactersService ganshinCharactersService) {
        this.ganshinCharactersService = ganshinCharactersService;
    }

    @GetMapping("/create")
    public String createCharacterPage() {
        return "characters/create";
    }

    @PostMapping("/create")
    public String createCharacter(@RequestParam("file") MultipartFile file, GanshinCharacter character) throws IOException {
        ganshinCharactersService.save(character,file);
        return "redirect:/characters";
    }

}
