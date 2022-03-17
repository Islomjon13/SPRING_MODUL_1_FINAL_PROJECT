package com.example.spring_modul_1_final_project.controller;

import com.example.spring_modul_1_final_project.payload.ProductDto;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public Response add(@RequestBody ProductDto productDto) {
        return productService.add(productDto);
    }

    @GetMapping("/get")
    public Response get() {
        return productService.get();
    }

    @GetMapping("/get/{id}")
    public Response getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping("/deactivate/{id}")
    public Response deactivate(@PathVariable Long id) {
        return productService.deactivate(id);
    }

    @PutMapping("/activate/{id}")
    public Response activate(@PathVariable Long id) {
        return productService.activate(id);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public Response edit(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.edit(id, productDto);
    }
}
