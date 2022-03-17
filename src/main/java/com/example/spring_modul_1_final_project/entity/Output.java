package com.example.spring_modul_1_final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Output {
    //generate not repeated code for product
    private static Integer generateCode = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @OneToOne
    private WareHouse wareHouse;

    @OneToOne
    private Currency currency;

    private Integer factureNumber;

    @Column(unique = true)
    private Integer code = generateCode++;

    @OneToOne
    private Client client;
}
