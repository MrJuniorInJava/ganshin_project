package com.example.Ganshin.App.models;

import javax.persistence.*;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "character_id",referencedColumnName = "id")
    private GanshinCharacter character;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public GanshinCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GanshinCharacter character) {
        this.character = character;
    }
}
