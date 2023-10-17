package com.example.Ganshin.models;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private int value;
    @ManyToOne
    @JoinColumn(name = "character_id",referencedColumnName = "id")
    private GanshinCharacter character;

    public Properties() {
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public GanshinCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GanshinCharacter character) {
        this.character = character;
    }
}
