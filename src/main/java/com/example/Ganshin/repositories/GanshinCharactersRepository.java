package com.example.Ganshin.repositories;

import com.example.Ganshin.models.GanshinCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GanshinCharactersRepository extends JpaRepository<GanshinCharacter,Integer> {
    public Optional<GanshinCharacter> findByName(String name);
}
