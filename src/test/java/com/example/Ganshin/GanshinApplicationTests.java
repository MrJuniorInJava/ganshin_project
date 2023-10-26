package com.example.Ganshin;

import com.example.Ganshin.controllers.GanshinCharacterController;
import com.example.Ganshin.models.GanshinCharacter;
import com.example.Ganshin.services.GanshinCharactersService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class GanshinApplicationTests {

    private final MockMvc mockMvc;
    private final GanshinCharacterController controller;
    private final GanshinCharactersService ganshinCharactersService;

    @Autowired
    GanshinApplicationTests(MockMvc mockMvc, GanshinCharacterController controller, GanshinCharactersService ganshinCharactersService) {
        this.mockMvc = mockMvc;
        this.controller = controller;
        this.ganshinCharactersService = ganshinCharactersService;
    }

    @Test
    void contextLoads() throws Exception {//тестируем то, что наше приложение запускается без ошибок
        assertThat(controller).isNotNull();
    }

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc
                .perform(get("/characters"))//запрос приходит на данный адрес
                .andDo(print())//Вывести результаты текста в консоль
                .andExpect(status().isOk())//Проверить, что статус ок
                .andExpect(content()//Проверить присутсвует ли данный контент на странице
                        .string(containsString("Персонажи Ganshin")))
                .andExpect(content()
                        .string(containsString("Добавить персонажа")));
    }

    @Test
    void characterShowCaseTest() throws Exception {
        this.mockMvc
                .perform(get("/characters/10"))//запрос приходит на данный адрес
                .andDo(print())//Вывести результаты текста в консоль
                .andExpect(status().isOk())//Проверить, что статус ок
                .andExpect(xpath("/html/body/b/span").string("Кадзуха"));//Проверяем, что на загруженной
        // странице есть такая надпись в этом html Блоке
    }

    @Test
    public void showCharacterServiceTest() {

        Assertions.assertEquals(10, ganshinCharactersService.findOne(10).get().getId());
    }



}
