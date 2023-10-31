package com.example.Ganshin.App.repositories;

import com.example.Ganshin.App.models.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeaponsRepository extends JpaRepository<Weapon,Integer> {
    Optional<Weapon> findByName(String name);
}
