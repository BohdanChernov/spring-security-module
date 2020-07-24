package com.springsecurity.module.dao;

import com.springsecurity.module.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDAO extends JpaRepository<Photo, Long> {
}
