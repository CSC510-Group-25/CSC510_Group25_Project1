package com.example.springsocial.controller;

import com.example.springsocial.model.Order;
import com.example.springsocial.payload.OrderRequest;
import com.example.springsocial.repository.OrderRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * What: The Order Controller is responsible for processing incoming REST API's requests for Order based operations.
 *
 * How: The common use cases of this class is to allow the user to add/edit/remove orders from the database by
 * processing the user request from the frontend.
 */

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/addOrder")
    /**
     * This method is called when the user requests to add a new order into the inventory database.
     * The Api takes input Order details
     * Api Parameter
     * Order ID
     * Dish ID
     * Order Quantity
     * Restaurant ID
     * Dish Name
     * Date
     */
    public String addOrder(@Valid @RequestBody OrderRequest orderRequest){
        Order order = new Order();

        order.setOrderId(orderRequest.getOrderId());
        order.setDishId(orderRequest.getDishId());
        order.setOrderQuantity(orderRequest.getOrderQuantity());
        order.setRestaurantId(orderRequest.getRestaurantId());
        order.setDishName(orderRequest.getDishName());
        order.setDate(orderRequest.getDate());

        Order result = orderRepository.save(order);
        String outputString = "{Order Added Successfully}";
        outputString =  new Gson().toJson(outputString);
        return outputString;
    }

    @GetMapping("/getAllOrders")
    /**
     * This method is used to call all the orders in the inventory.
     */
    public String getAllOrders(){

        List<Order> order =  orderRepository.findAll();
        String orderJson = new Gson().toJson(order);
        return orderJson;

    }


}
