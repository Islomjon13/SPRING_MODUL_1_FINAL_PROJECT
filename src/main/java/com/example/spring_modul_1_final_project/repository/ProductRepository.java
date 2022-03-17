package com.example.spring_modul_1_final_project.repository;

import com.example.spring_modul_1_final_project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameAndCategoryId(String name, Long category_id);
}
