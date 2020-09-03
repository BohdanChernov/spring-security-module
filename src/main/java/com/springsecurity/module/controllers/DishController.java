package com.springsecurity.module.controllers;

import com.springsecurity.module.dao.DishDAO;
import com.springsecurity.module.dao.PhotoDAO;
import com.springsecurity.module.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/dishes/")
public class DishController {
    private PhotoDAO photoDAO;
    private DishDAO dishDAO;

    @Autowired
    public DishController(DishDAO dishDAO, PhotoDAO photoDAO) {
        this.dishDAO = dishDAO;
        this.photoDAO = photoDAO;
    }

    @GetMapping("/")
    public String getPage() {
        return "api/dishes/";
    }

    @PostMapping(path = "create")
    @PreAuthorize("hasAuthority('dish:write')")
    public void createDish(@RequestBody Dish dish) {
        dishDAO.save(dish);
    }

    @GetMapping(path = "read/{id}")
    @PreAuthorize("hasAuthority('dish:read')")
    public Dish readDish(@PathVariable("id") Long id) {
        return dishDAO.findById(id).orElseThrow(() -> new NullPointerException(String.format("Dish %s is not found", id)));
    }

    @PutMapping(path = "update/{id}")
    @PreAuthorize("hasAuthority('dish:write')")
    public void updateDish(@PathVariable("id") Long id, @RequestBody Dish dish) {
        Dish dishToUpdate = dishDAO.findById(id).orElseThrow(() -> new NullPointerException(String.format("Dish %s is not found", id)));
        dishToUpdate.setName(dish.getName());
        dishToUpdate.setDescription(dish.getDescription());
        dishDAO.save(dishToUpdate);
    }

    @DeleteMapping(path = "delete/{id}")
    @PreAuthorize("hasAuthority('dish:write')")
    public void deleteDish(@PathVariable("id") Long id) {
        dishDAO.deleteById(id);
    }

}
