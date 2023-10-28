package com.example.Ganshin.App.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "character")
public class GanshinCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(unique = true, name = "name")
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2,max = 30, message = "Имя должно содержать от 2 до 30 символов")
    private String name;
    @Column(name = "preview_image_id")
    private int previewImageId;
    @OneToMany(mappedBy = "character")
    private List<Property> properties;
    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
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

    public void addImageToCharacter(Image image) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(image);
        image.setCharacter(this);
    }
    public void addPropertiesToCharacter(Property property){
        property.setCharacter(this);
        if(this.properties==null){
            this.properties=new ArrayList<>();
        }
        this.properties.add(property);
    }
}
