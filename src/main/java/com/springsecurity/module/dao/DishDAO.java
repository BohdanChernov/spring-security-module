package com.springsecurity.module.dao;

import com.springsecurity.module.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishDAO extends JpaRepository<Dish, Long> {
}
