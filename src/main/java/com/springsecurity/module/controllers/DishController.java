package com.springsecurity.module.controllers;

import com.springsecurity.module.dao.DishDAO;
import com.springsecurity.module.dao.PhotoDAO;
import com.springsecurity.module.dto.DishDTO;
import com.springsecurity.module.models.Dish;
import com.springsecurity.module.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
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
        return "Hello it's the dish controller.";
    }

    @PostMapping(path = "create", produces = "application/json")
    public void createDish(@RequestBody DishDTO dishDTO) {
        Dish newDish = new Dish(dishDTO.getName(), dishDTO.getDescription());
        dishDAO.save(newDish);
        List<String> photoPaths = dishDTO.getPhotoPaths();
        photoPaths.forEach(photoPath -> photoDAO.save(new Photo(photoPath, newDish)));
    }

    @GetMapping(path = "read/{id}")
    public String readDish(@PathVariable("id") Long id) {
        Optional<Dish> dish = dishDAO.findById(id);
        return dish.orElseThrow(() -> new NullPointerException("The dish is not founded")).toString();
    }

    @PutMapping(path = "update/{id}")
    public void updateDish(@PathVariable("id") Long id, @RequestBody DishDTO dishDTO) {
        Optional<Dish> dishToUpdate = dishDAO.findById(id);
        Dish dish = dishToUpdate.orElseThrow(() -> new NullPointerException("The dish is not founded"));
        dish.setName(dishDTO.getName());
        dish.setDescription(dishDTO.getDescription());
        dishDAO.save(dish);
    }

    @DeleteMapping(path = "delete/{id}")
    public String deleteDish(@PathVariable("id") Long id) {
        return null;
    }

}
