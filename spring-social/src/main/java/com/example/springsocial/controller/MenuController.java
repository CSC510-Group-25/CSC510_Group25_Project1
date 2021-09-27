package com.example.springsocial.controller;

import com.example.springsocial.model.Menu;
import com.example.springsocial.payload.MenuRequest;
import com.example.springsocial.repository.MenuRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class MenuController{

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping("/addMenu")
    public String addMenu(@Valid @RequestBody MenuRequest MenuRequest){

        Menu menu = new Menu();
        menu.setRestaurantId(MenuRequest.getRestaurantID());
        menu.setDishId(MenuRequest.getDishID());
        menu.setDishName(MenuRequest.getDishName());
        menu.setDishPointer(MenuRequest.getDishPointer());
        menu.setCostPerDish(MenuRequest.getCostPerDish());

        Menu result = menuRepository.save(menu);
        return "{Menu Added Successfully}";
    }

//    @GetMapping("/getAllMenus")
//    public String getAllMenus() {
//
//        List<Menu> menu = menuRepository.findAll();
//        String menuJson = new Gson().toJson(menu);
//        return menuJson;
//    }

}