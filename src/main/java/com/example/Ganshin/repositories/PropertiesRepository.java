package com.example.Ganshin.repositories;


import com.example.Ganshin.models.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository  extends JpaRepository<Properties,Integer> {
}
