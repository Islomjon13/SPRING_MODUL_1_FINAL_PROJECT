package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.User;
import com.example.spring_modul_1_final_project.entity.User;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.UserRepository;
import com.example.spring_modul_1_final_project.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements Messages {
    private final UserRepository userRepository;

    public Response add(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String phoneNumber = user.getPhoneNumber();
        boolean b = userRepository.existsByFirstNameAndLastNameOrPhoneNumber(firstName, lastName, phoneNumber);
        if (b)
            return new Response(EXIST, false);
        userRepository.save(user);
        return new Response(ADDED, true);
    }

    public Response get() {
        List<User> all = userRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS, true, byId.get());
    }

    public Response deactivate(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setActive(false);
            return new Response(DEACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response activate(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setActive(true);
            return new Response(ACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response delete(Long id) {
        boolean b = userRepository.existsById(id);
        if (b) {
            userRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, User newUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) return new Response(NOT_FOUND, false);
        User user = optionalUser.get();
        String firstName = newUser.getFirstName();
        String lastName = newUser.getLastName();
        String phoneNumber = newUser.getPhoneNumber();
        String password = newUser.getPassword();
        if (firstName != null) user.setFirstName(firstName);
        if (lastName != null) user.setFirstName(lastName);
        if (phoneNumber != null) user.setFirstName(phoneNumber);
        if (password != null) user.setFirstName(password);
        userRepository.save(user);
        return new Response(EDITED, true);
    }
}
