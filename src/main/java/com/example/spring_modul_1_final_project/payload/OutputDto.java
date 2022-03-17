package com.example.spring_modul_1_final_project.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputDto {
    private Timestamp date;
    private Long wareHouseId;
    private Long currencyId;
    private Integer factureNumber;
    private Long clientId;
}
