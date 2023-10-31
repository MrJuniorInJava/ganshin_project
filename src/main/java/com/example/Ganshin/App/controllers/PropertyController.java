package com.example.Ganshin.App.controllers;

import com.example.Ganshin.App.models.Property;
import com.example.Ganshin.App.services.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PropertyController {

    private final PropertiesService propertiesService;

    @Autowired
    public PropertyController(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @PostMapping("/property/add/{idCharacter}")
    public String addProperty(@PathVariable("idCharacter") int id, @ModelAttribute("properties") Property property) throws IOException {
        propertiesService.addProperty(property, id);
        return "redirect:/characters/{idCharacter}";
    }

    @PostMapping("/property/delete/{idCharacter}/{idProperty}")
    public String deleteProperty(@PathVariable("idCharacter") int idCharacter ,@PathVariable("idProperty") int idProperty) {
        propertiesService.delete(idProperty);
        return "redirect:/characters/{idCharacter}";
    }
}
