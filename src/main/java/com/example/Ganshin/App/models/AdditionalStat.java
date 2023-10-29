package com.example.Ganshin.App.models;

import javax.persistence.*;

@Entity
@Table(name = "additional_stats")
public class AdditionalStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne
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

    public GanshinCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GanshinCharacter character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
