package com.example.spring_modul_1_final_project.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputProductDto {
    private Long   productId;
    private Double amount;
    private Double price;
    private Timestamp expireDate;
    private Long inputId;
}
