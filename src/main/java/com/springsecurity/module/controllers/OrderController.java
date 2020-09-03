package com.springsecurity.module.controllers;

import com.springsecurity.module.dao.FoodOrderDAO;
import com.springsecurity.module.models.FoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders/")
public class OrderController {
    private FoodOrderDAO foodOrderDAO;

    @Autowired
    public OrderController(FoodOrderDAO foodOrderDAO) {
        this.foodOrderDAO = foodOrderDAO;
    }

    @GetMapping("/")
    public String getPage() {
        return "api/orders/";
    }

    @PostMapping("create")
    @PreAuthorize("hasAuthority('order:write')")
    public void createOrder(@RequestBody FoodOrder foodOrder) {
        foodOrderDAO.save(foodOrder);
    }

    @GetMapping(path = "read/{id}")
    @PreAuthorize("hasAuthority('order:read')")
    public FoodOrder readOrder(@PathVariable("id") Long id) {
        return foodOrderDAO.findById(id).orElseThrow(() -> new NullPointerException(String.format("Order %s is not found", id)));
    }

    @PutMapping(path = "update/{id}")
    @PreAuthorize("hasAuthority('order:write')")
    public void updateOrder(@PathVariable("id") Long id, @RequestBody FoodOrder foodOrder) {
        FoodOrder foodOrderToUpdate = foodOrderDAO.findById(id).orElseThrow(() -> new NullPointerException(String.format("Order %s is not found", id)));
        foodOrderToUpdate.setName(foodOrder.getName());
        foodOrderDAO.save(foodOrderToUpdate);
    }

    @DeleteMapping(path = "delete/{id}")
    @PreAuthorize("hasAuthority('order:write')")
    public void deleteOrder(@PathVariable("id") Long id) {
        foodOrderDAO.deleteById(id);
    }

}
