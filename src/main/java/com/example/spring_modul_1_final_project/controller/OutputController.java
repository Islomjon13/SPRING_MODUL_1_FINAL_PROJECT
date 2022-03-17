package com.example.spring_modul_1_final_project.controller;

import com.example.spring_modul_1_final_project.payload.OutputDto;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.service.OutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/output")
public class OutputController {
    private final OutputService outputService;

    @PostMapping("/add")
    public Response add(@RequestBody OutputDto outputDto) {
        return outputService.add(outputDto);
    }

    @GetMapping("/get")
    public Response get() {
        return outputService.get();
    }

    @GetMapping("/get/{id}")
    public Response getById(@PathVariable Long id) {
        return outputService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return outputService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public Response edit(@PathVariable Long id, @RequestBody OutputDto outputDto) {
        return outputService.edit(id, outputDto);
    }
}
