package com.example.spring_modul_1_final_project.controller;

import com.example.spring_modul_1_final_project.payload.InputDto;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.service.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/input")
public class InputController {
    private final InputService inputService;

    @PostMapping("/add")
    public Response add(@RequestBody InputDto inputDto) {
        return inputService.add(inputDto);
    }

    @GetMapping("/get")
    public Response get() {
        return inputService.get();
    }

    @GetMapping("/get/{id}")
    public Response getById(@PathVariable Long id) {
        return inputService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return inputService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public Response edit(@PathVariable Long id, @RequestBody InputDto inputDto) {
        return inputService.edit(id, inputDto);
    }
}
