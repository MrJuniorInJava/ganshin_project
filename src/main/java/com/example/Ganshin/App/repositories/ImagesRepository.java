package com.example.Ganshin.App.repositories;

import com.example.Ganshin.App.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository  extends JpaRepository<Image,Integer> {
}
