package com.example.Ganshin.App.repositories;

import com.example.Ganshin.App.models.AdditionalStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalStatsRepository extends JpaRepository<AdditionalStat,Integer> {
}
