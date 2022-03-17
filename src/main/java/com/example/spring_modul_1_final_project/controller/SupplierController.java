package com.example.spring_modul_1_final_project.controller;

import com.example.spring_modul_1_final_project.entity.Supplier;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping("/add")
    public Response add(@RequestBody Supplier supplier) {
        return supplierService.add(supplier);
    }

    @GetMapping("/get")
    public Response get() {
        return supplierService.get();
    }

    @GetMapping("/get/{id}")
    public Response getById(@PathVariable Long id) {
        return supplierService.getById(id);
    }

    @PutMapping("/deactivate/{id}")
    public Response deactivate(@PathVariable Long id) {
        return supplierService.deactivate(id);
    }

    @PutMapping("/activate/{id}")
    public Response activate(@PathVariable Long id) {
        return supplierService.activate(id);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return supplierService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public Response edit(@PathVariable Long id, @RequestBody Supplier supplier) {
        return supplierService.edit(id, supplier);
    }
}
