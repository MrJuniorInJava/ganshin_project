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
    @Size(min = 2, max = 30, message = "Имя должно содержать от 2 до 30 символов")
    private String name;
    @Column(name = "preview_image_id")
    private int previewImageId;
    @OneToMany(mappedBy = "character")
    private List<Property> properties;
    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;
    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;
    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private List<AdditionalStat> stats;
    @ManyToMany(mappedBy = "characters")
    private List<Weapon> weapons;

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<AdditionalStat> getStats() {
        return stats;
    }

    public void setStats(List<AdditionalStat> stats) {
        this.stats = stats;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    //Вспомогательные методы
    public void addImageToCharacter(Image image) {
        image.setCharacter(this);
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(image);
    }

    public void addPropertyToCharacter(Property property) {
        property.setCharacter(this);
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }
        this.properties.add(property);
    }

    public void addItemToCharacter(Item item) {
        item.setCharacter(this);
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }
    public void addStatToCharacter(AdditionalStat stat) {
        stat.setCharacter(this);
        if (this.stats == null) {
            this.stats = new ArrayList<>();
        }
        this.stats.add(stat);
    }
    public void addWeaponToCharacter(Weapon weapon) {
        if (this.weapons == null) {
            this.weapons = new ArrayList<>();
        }
        this.weapons.add(weapon);
    }
}
