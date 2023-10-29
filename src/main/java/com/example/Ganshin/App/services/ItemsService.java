package com.example.Ganshin.App.services;

import com.example.Ganshin.App.models.GanshinCharacter;
import com.example.Ganshin.App.models.Item;
import com.example.Ganshin.App.repositories.GanshinCharactersRepository;
import com.example.Ganshin.App.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ItemsService {
    private final ItemsRepository itemsRepository;
    private final GanshinCharactersRepository ganshinCharactersRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository, GanshinCharactersRepository ganshinCharactersRepository) {
        this.itemsRepository = itemsRepository;
        this.ganshinCharactersRepository = ganshinCharactersRepository;
    }
    @Transactional
    public void addItem(Item item, int idCharacter) {
        GanshinCharacter character = ganshinCharactersRepository.findById(idCharacter).orElse(null);
        character.addItemToCharacter(item);
        switch (character.getItems().indexOf(item)) {
            case 0 -> item.setName("Цветок");
            case 1 -> item.setName("Перо");
            case 2 -> item.setName("Часы");
            case 3 -> item.setName("Кубок");
            case 4 -> item.setName("Шапка");
            default -> throw new RuntimeException("Предмета, к которому вы пытаетесь добавить статы, не существует");
        }
        itemsRepository.save(item);
    }
    @Transactional
    public void delete(int idItem) {
        Item item = itemsRepository.findById(idItem).orElse(null);
        item.getCharacter().getItems().remove(item);
        itemsRepository.deleteById(idItem);
    }
}
