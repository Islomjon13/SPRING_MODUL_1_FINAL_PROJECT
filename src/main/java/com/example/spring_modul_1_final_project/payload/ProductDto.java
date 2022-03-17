package com.example.spring_modul_1_final_project.payload;

import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private Long categoryId;

    private Long attachmentCollectionId;

    private Long measurementId;
}
