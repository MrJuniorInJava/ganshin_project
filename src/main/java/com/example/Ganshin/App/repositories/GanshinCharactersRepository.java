package com.example.Ganshin.App.repositories;

import com.example.Ganshin.App.models.GanshinCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GanshinCharactersRepository extends JpaRepository<GanshinCharacter,Integer> {
    public Optional<GanshinCharacter> findByName(String name);
    List<GanshinCharacter> findByNameStartingWith(String name);
}
