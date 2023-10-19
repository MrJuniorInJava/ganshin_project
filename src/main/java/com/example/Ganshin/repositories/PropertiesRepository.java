package com.example.Ganshin.repositories;


import com.example.Ganshin.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository  extends JpaRepository<Property,Integer> {
}
