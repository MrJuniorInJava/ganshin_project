package com.example.Ganshin.App.controllers;

import com.example.Ganshin.App.models.AdditionalStat;
import com.example.Ganshin.App.services.AdditionalStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdditionalStatController {
    private final AdditionalStatsService service;

    @Autowired
    public AdditionalStatController(AdditionalStatsService service) {
        this.service = service;
    }
    @PostMapping("/stats/add/{idCharacter}")
    public String addStats(@PathVariable("idCharacter") int idCharacter,  @ModelAttribute("stats") AdditionalStat stat) {
        service.addStat(stat, idCharacter);
        return "redirect:/characters/{idCharacter}";
    }
    @PostMapping("/stats/delete/{idCharacter}/{idStat}")
    public String deleteItem(@PathVariable("idCharacter") int idCharacter,@PathVariable("idStat") int idStat) {
        service.deleteStat(idStat);
        return "redirect:/characters/{idCharacter}";
    }

}
