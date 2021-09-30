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
import java.util.List;

/**
 * What: The Menu Controller is responsible for processing incoming REST API's requests.
 *
 * How: The common use cases of this class is to allow the user to add/edit/remove menus from the database by
 * processing the user request from the frontend.
 */
@RestController
public class MenuController{

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping("/addMenu")
    /**
     * This method is called when the user requests to add a new dish into the menu database.
     * It does this by taking the request and then setting the parameters given into their respective variables of the
     * database and then saving all the information into the database.
     */
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

   @GetMapping("/getAllMenus")
   /**
    * This method is used to call all the dishes in the menu.
    */
    public String getAllMenus() {
        List<Menu> menu = menuRepository.findAll();
        String menuJson = new Gson().toJson(menu);
        return menuJson;
    }

}