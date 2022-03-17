package com.example.spring_modul_1_final_project.payload;

import lombok.Data;

@Data
public class CategoryDto {
    private String name;
    private Long parentCategoryId;
}

