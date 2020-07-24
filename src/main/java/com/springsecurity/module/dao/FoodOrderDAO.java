package com.springsecurity.module.dao;

import com.springsecurity.module.models.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOrderDAO extends JpaRepository<FoodOrder, Long> {
}
