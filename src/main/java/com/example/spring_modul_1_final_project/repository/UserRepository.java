package com.example.spring_modul_1_final_project.repository;

import com.example.spring_modul_1_final_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Size;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByFirstNameAndLastNameOrPhoneNumber(String firstName, String lastName, @Size(min = 9, max = 13) String phoneNumber);
}
