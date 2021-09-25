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

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/addOrder")
    public String addOrder(@Valid @RequestBody OrderRequest orderRequest){
        Order order = new Order();

        order.setOrderId(orderRequest.getOrderId());
        order.setDishId(orderRequest.getDishId());
        order.setOrderQuantity(orderRequest.getOrderQuantity());
        order.setRestaurantId(orderRequest.getRestaurantId());
        order.setDishName(orderRequest.getDishName());
        order.setDate(orderRequest.getDate());

        Order result = orderRepository.save(order);
        return "{Order Added Successfully}";
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders(){

        List<Order> order =  orderRepository.findAll();
//        String orderJson = new Gson().toJson(order);
        return order;

    }


}
