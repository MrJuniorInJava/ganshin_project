package com.example.Ganshin.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "character")
public class GanshinCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "preview_image_id")
    private int previewImageId;
    @OneToMany(mappedBy = "character")
    private List<Properties> properties;
    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Image> images;

    public GanshinCharacter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

public int getPreviewImageId() {
        return previewImageId;
    }

    public void setPreviewImageId(int previewImageId) {
        this.previewImageId = previewImageId;
    }

    public void addImageToCharacter(Image image){
        image.setCharacter(this);
        images = new ArrayList<>();
        images.add(image);
    }
}
