package com.example.springsocial.controller;


import com.example.springsocial.model.Inventory;
import com.example.springsocial.repository.InventoryRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InventoryController.class)
@WebAppConfiguration
@EnableWebMvc
public class InventoryControllerTests {

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
    private Inventory inventory;

    @MockBean
    private InventoryRepository inventoryRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        String uri = "/getAllInventory";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void Validate_addOrders() throws Exception {
        String uri = "/addInventory";

        Inventory demo_inventory = new Inventory();
        demo_inventory.setBatchID("b12");
        demo_inventory.setBatchQty(10);
        demo_inventory.setCostPerItem(100);
        demo_inventory.setItemName("Coke");
        demo_inventory.setRestaurantID("r10");
        demo_inventory.setRestaurantName("demo_res");
        demo_inventory.setItemID("i10");


        String demoInventoryDemo = new Gson().toJson(demo_inventory);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(demoInventoryDemo)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

}
