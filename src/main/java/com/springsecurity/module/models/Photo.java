package com.springsecurity.module.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path;
    @ManyToOne
    private Dish dish;

    public Photo(String path, Dish dish) {
        this.path = path;
        this.dish = dish;
    }
}
