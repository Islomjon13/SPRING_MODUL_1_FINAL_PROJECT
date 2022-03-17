package com.example.spring_modul_1_final_project.repository;

import com.example.spring_modul_1_final_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
