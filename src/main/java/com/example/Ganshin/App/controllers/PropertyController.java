package com.example.Ganshin.App.controllers;

import com.example.Ganshin.App.services.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PropertyController {

    private final PropertiesService propertiesService;

    @Autowired
    public PropertyController(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @PostMapping("/property/delete/{idCharacter}/{idProperty}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProperty(@PathVariable("idCharacter") int idCharacter ,@PathVariable("idProperty") int idProperty) {
        propertiesService.delete(idProperty);
        return "redirect:/characters/{idCharacter}";
    }
}
