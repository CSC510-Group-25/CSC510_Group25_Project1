package com.example.springsocial.controller;

import com.example.springsocial.config.SecurityConfig;
import com.example.springsocial.model.Menu;
import com.example.springsocial.repository.MenuRepository;
import com.example.springsocial.security.CustomUserDetailsService;
import com.example.springsocial.security.oauth2.CustomOAuth2UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MenuController.class)
@WebAppConfiguration
public class MenuControllerTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    public String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    public <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @MockBean
    private Menu menu;

    @MockBean
    private MenuRepository menuRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        String uri = "/getAllMenus";
        Menu demo_menu = new Menu();
        demo_menu.setRestaurantId("r10");
        demo_menu.setDishId("o10");
        demo_menu.setDishName("d10");
        demo_menu.setDishPointer("dp10");
        demo_menu.setCostPerDish(1.0);

        List<Menu> demo_list = new ArrayList<Menu>(Arrays.asList(demo_menu));
        String demoMenuJson = new Gson().toJson(demo_list);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void Validate_addMenus() throws Exception {
        String uri = "/addMenu";

        Menu demo_menu = new Menu();
        demo_menu.setRestaurantId("r10");
        demo_menu.setDishId("o10");
        demo_menu.setDishName("d10");
        demo_menu.setDishPointer("dp10");
        demo_menu.setCostPerDish(1.0);
        List<Menu> demo_list = new ArrayList<Menu>(Arrays.asList(demo_menu));
        String demoMenuJson = new Gson().toJson(demo_list);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(demoMenuJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

}
