package com.example.Ganshin.App.repositories;

import com.example.Ganshin.App.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item,Integer> {
}
