package com.example.spring_modul_1_final_project.repository;

import com.example.spring_modul_1_final_project.entity.Currency;
import com.example.spring_modul_1_final_project.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    boolean existsByName(String name);
}
