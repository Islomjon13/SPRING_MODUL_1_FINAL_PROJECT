package com.example.spring_modul_1_final_project.repository;

import com.example.spring_modul_1_final_project.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
