package com.example.Ganshin;

import com.example.Ganshin.App.controllers.GanshinCharacterControllerForAdmin;
import com.example.Ganshin.App.services.GanshinCharactersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class GanshinApplicationTests {

    private final MockMvc mockMvc;
    private final GanshinCharacterControllerForAdmin controller;
    private final GanshinCharactersService ganshinCharactersService;

    @Autowired
    GanshinApplicationTests(MockMvc mockMvc, GanshinCharacterControllerForAdmin controller, GanshinCharactersService ganshinCharactersService) {
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
