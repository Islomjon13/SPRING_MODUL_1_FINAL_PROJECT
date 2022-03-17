package com.example.spring_modul_1_final_project.controller;

import com.example.spring_modul_1_final_project.payload.CategoryDto;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public Response add(@RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @GetMapping("/get")
    public Response get() {
        return categoryService.get();
    }

    @GetMapping("/get/{id}")
    public Response getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PutMapping("/deactivate/{id}")
    public Response deactivate(@PathVariable Long id) {
        return categoryService.deactivate(id);
    }

    @PutMapping("/activate/{id}")
    public Response activate(@PathVariable Long id) {
        return categoryService.activate(id);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public Response edit(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return categoryService.edit(id, categoryDto);
    }
}
