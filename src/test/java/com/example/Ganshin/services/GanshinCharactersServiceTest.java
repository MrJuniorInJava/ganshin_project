package com.example.Ganshin.services;

import com.example.Ganshin.App.repositories.GanshinCharactersRepository;
import com.example.Ganshin.App.services.GanshinCharactersService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GanshinCharactersServiceTest {
    @InjectMocks
    private GanshinCharactersService ganshinCharactersService;
    @Mock
    private GanshinCharactersRepository ganshinCharactersRepository;

    public void shouldReturnAllCharacters(){

    }
}
