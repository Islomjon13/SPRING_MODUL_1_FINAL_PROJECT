package com.example.spring_modul_1_final_project.repository;

import com.example.spring_modul_1_final_project.entity.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WareHouseRepository extends JpaRepository<WareHouse,Long> {
    boolean existsByName(String name);
}
