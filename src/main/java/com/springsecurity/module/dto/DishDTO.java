package com.springsecurity.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DishDTO {
    private String name;
    private String description;
    private List<String> photoPaths;
}
