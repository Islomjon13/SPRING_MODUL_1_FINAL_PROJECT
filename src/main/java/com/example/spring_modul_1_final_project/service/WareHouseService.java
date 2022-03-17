package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.User;
import com.example.spring_modul_1_final_project.entity.WareHouse;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.UserRepository;
import com.example.spring_modul_1_final_project.repository.WareHouseRepository;
import com.example.spring_modul_1_final_project.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WareHouseService implements Messages {
    private final WareHouseRepository wareHouseRepository;
    private final UserRepository userRepository;

    public Response add(WareHouse wareHouse) {
        boolean b = wareHouseRepository.existsByName(wareHouse.getName());
        if (b)
            return new Response(EXIST, false);
        wareHouseRepository.save(wareHouse);
        return new Response(ADDED, true);
    }

    public Response get() {
        List<WareHouse> all = wareHouseRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<WareHouse> byId = wareHouseRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS, true, byId.get());
    }

    public Response deactivate(Long id) {
        Optional<WareHouse> byId = wareHouseRepository.findById(id);
        if (byId.isPresent()) {
            WareHouse wareHouse = byId.get();
            wareHouse.setActive(false);
            return new Response(DEACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response activate(Long id) {
        Optional<WareHouse> byId = wareHouseRepository.findById(id);
        if (byId.isPresent()) {
            WareHouse wareHouse = byId.get();
            wareHouse.setActive(true);
            return new Response(ACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response delete(Long id) {
        boolean b = wareHouseRepository.existsById(id);
        if (b) {
            wareHouseRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, WareHouse newWareHouse) {
        Optional<WareHouse> byId = wareHouseRepository.findById(id);
        if (byId.isPresent()) {
            WareHouse wareHouse = byId.get();
            String name = newWareHouse.getName();
            if (name != null)
                wareHouse.setName(name);
            wareHouseRepository.save(wareHouse);
            return new Response(EDITED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response addUser(Long wareHouseId, Long userId) {
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(wareHouseId);
        if (optionalWareHouse.isEmpty()) return new Response(WARE_HOUSE_NOT_FOUND, false);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) return new Response(USER_NOT_FOUND, false);

        WareHouse wareHouse = optionalWareHouse.get();
        User user = optionalUser.get();
        Set<User> users = wareHouse.getUsers();
        users.add(user);
        wareHouse.setUsers(users);
        wareHouseRepository.save(wareHouse);
        return new Response(USER_ADDED_TO_WARE_HOUSE, true);
    }
}
