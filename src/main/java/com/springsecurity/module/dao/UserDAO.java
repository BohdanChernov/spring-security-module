package com.springsecurity.module.dao;

import com.springsecurity.module.models.UserImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<UserImpl, Long> {
    Optional<UserImpl> findByName(String name);
}
