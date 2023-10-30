package com.example.Ganshin.App.services;

import com.example.Ganshin.App.models.AdditionalStat;
import com.example.Ganshin.App.models.GanshinCharacter;
import com.example.Ganshin.App.repositories.AdditionalStatsRepository;
import com.example.Ganshin.App.repositories.GanshinCharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdditionalStatsService {
    private final AdditionalStatsRepository statsRepository;
    private final GanshinCharactersRepository ganshinCharactersRepository;

    @Autowired
    public AdditionalStatsService(AdditionalStatsRepository statsRepository, GanshinCharactersRepository ganshinCharactersRepository) {
        this.statsRepository = statsRepository;
        this.ganshinCharactersRepository = ganshinCharactersRepository;
    }
    @Transactional
    public void addStat(AdditionalStat stat, int idCharacter){
        GanshinCharacter character = ganshinCharactersRepository.findById(idCharacter).orElse(null);
        character.addStatToCharacter(stat);
        statsRepository.save(stat);
    }
    @Transactional
    public void deleteStat(int idStat) {
        AdditionalStat stat = statsRepository.findById(idStat).orElse(null);
        stat.getCharacter().getStats().remove(stat);
        statsRepository.deleteById(idStat);
    }

}
