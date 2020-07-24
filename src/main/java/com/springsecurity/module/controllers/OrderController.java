package com.springsecurity.module.controllers;

import com.springsecurity.module.dao.FoodOrderDAO;
import com.springsecurity.module.models.FoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/orders/")
public class OrderController {
    private FoodOrderDAO foodOrderDAO;

    @Autowired
    public OrderController(FoodOrderDAO foodOrderDAO) {
        this.foodOrderDAO = foodOrderDAO;
    }

    @GetMapping("/")
    public String getPage() {
        return null;
    }

    @PostMapping("create")
    @PreAuthorize("hasAuthority('order:write')")
    public void createOrder(@RequestBody FoodOrder foodOrder) {
        foodOrderDAO.save(foodOrder);
    }

    @GetMapping(path = "read/{id}")
    @PreAuthorize("hasAuthority('order:read')")
    public String readOrder(@PathVariable("id") Long id) {
        return foodOrderDAO.findById(id).get().toString();
    }

    @PutMapping(path = "update/{id}")
    @PreAuthorize("hasAuthority('order:write')")
    public String updateOrder(@PathVariable("id") Long id, @RequestBody FoodOrder foodOrder) {
        return null;
    }

    @DeleteMapping(path = "delete/{id}")
    @PreAuthorize("hasAuthority('order:write')")
    public String deleteOrder(@PathVariable("id") Long id) {
        return null;
    }

}
