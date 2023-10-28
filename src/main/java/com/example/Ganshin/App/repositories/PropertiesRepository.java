package com.example.Ganshin.App.repositories;


import com.example.Ganshin.App.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository  extends JpaRepository<Property,Integer> {
}
