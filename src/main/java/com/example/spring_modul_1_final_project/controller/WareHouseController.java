package com.example.spring_modul_1_final_project.controller;

import com.example.spring_modul_1_final_project.entity.WareHouse;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.service.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wareHouse")
public class WareHouseController {
    private final WareHouseService wareHouseService;

    @PostMapping("/add")
    public Response add(@RequestBody WareHouse wareHouse) {
        return wareHouseService.add(wareHouse);
    }

    @GetMapping("/get")
    public Response get() {
        return wareHouseService.get();
    }

    @GetMapping("/get/{id}")
    public Response getById(@PathVariable Long id) {
        return wareHouseService.getById(id);
    }

    @PutMapping("/deactivate/{id}")
    public Response deactivate(@PathVariable Long id) {
        return wareHouseService.deactivate(id);
    }

    @PutMapping("/activate/{id}")
    public Response activate(@PathVariable Long id) {
        return wareHouseService.activate(id);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return wareHouseService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public Response edit(@PathVariable Long id, @RequestBody WareHouse wareHouse) {
        return wareHouseService.edit(id, wareHouse);
    }

    @PostMapping("/addUser/{wareHouseId}")
    public Response addUser(@PathVariable Long wareHouseId, @RequestParam Long userId) {
        return wareHouseService.addUser(wareHouseId, userId);
    }
}
