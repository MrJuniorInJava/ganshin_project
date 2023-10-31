package com.example.Ganshin.App.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weapon")
public class Weapon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(name = "weapon_character",
    joinColumns = @JoinColumn(name = "weapon_id"),
    inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<GanshinCharacter> characters;

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

    public List<GanshinCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(List<GanshinCharacter> characters) {
        this.characters = characters;
    }
    //Вспомогательные методы

    public void addCharacterToWeapon(GanshinCharacter character){
        if(this.characters==null){
            characters=new ArrayList<>();
        }
        this.characters.add(character);
        character.addWeaponToCharacter(this);
    }

    @Override
    public String toString() {
        return  name;
    }
}
