package com.springsecurity.module.controllers;

import com.springsecurity.module.models.Customer;
import com.springsecurity.module.models.FoodOrder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/customers/")
public class CustomerController {

    @GetMapping("/")
    public String getPage() {
        return null;
    }

    @PostMapping("register")
    public String registerCustomer(@RequestBody FoodOrder foodOrder) {
        return null;
    }

    @GetMapping(path = "read/{id}")
    public String readCustomer(@PathVariable("id") Long id) {
        return null;
    }

    @PutMapping(path = "update/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        return null;
    }

    @DeleteMapping(path = "delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        return null;
    }
}
