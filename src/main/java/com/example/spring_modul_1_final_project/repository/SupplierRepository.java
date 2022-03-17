package com.example.spring_modul_1_final_project.repository;

import com.example.spring_modul_1_final_project.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
