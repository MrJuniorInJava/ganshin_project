package com.example.Ganshin.App.controllers;

import com.example.Ganshin.App.models.Item;
import com.example.Ganshin.App.services.GanshinCharactersService;
import com.example.Ganshin.App.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ItemController {
    private final ItemsService itemsService;
    private final GanshinCharactersService ganshinCharactersService;

    @Autowired
    public ItemController(ItemsService itemsService, GanshinCharactersService ganshinCharactersService) {
        this.itemsService = itemsService;
        this.ganshinCharactersService = ganshinCharactersService;
    }

    @PostMapping("/items/add/{idCharacter}")
    public String addItem(@PathVariable("idCharacter") int idCharacter,  @ModelAttribute("items") Item item) {
        itemsService.addItem(item, idCharacter);
        return "redirect:/characters_admin/{idCharacter}";
    }
    @PostMapping("/items/delete/{idCharacter}/{idItem}")
    public String deleteItem(@PathVariable("idCharacter") int idCharacter, @PathVariable("idItem") int idItem) {
        itemsService.delete(idItem);
        return "redirect:/characters_admin/{idCharacter}";
    }
}
