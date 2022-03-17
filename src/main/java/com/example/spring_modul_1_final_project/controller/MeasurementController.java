package com.example.spring_modul_1_final_project.controller;

import com.example.spring_modul_1_final_project.entity.Measurement;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;

    @PostMapping("/add")
    public Response add(@RequestBody Measurement measurement) {
        return measurementService.add(measurement);
    }

    @GetMapping("/get")
    public Response get() {
        return measurementService.get();
    }

    @GetMapping("/get/{id}")
    public Response getById(@PathVariable Long id) {
        return measurementService.getById(id);
    }

    @PutMapping("/deactivate/{id}")
    public Response deactivate(@PathVariable Long id) {
        return measurementService.deactivate(id);
    }

    @PutMapping("/activate/{id}")
    public Response activate(@PathVariable Long id) {
        return measurementService.activate(id);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return measurementService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public Response edit(@PathVariable Long id, @RequestBody Measurement measurement) {
        return measurementService.edit(id, measurement);
    }
}
