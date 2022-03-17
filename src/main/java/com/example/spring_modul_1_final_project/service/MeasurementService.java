package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.utils.Messages;
import com.example.spring_modul_1_final_project.entity.Measurement;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementService implements Messages {
    private final MeasurementRepository measurementRepository;

    public Response add(Measurement measurement) {
        boolean b = measurementRepository.existsByName(measurement.getName());
        if (b)
            return new Response(EXIST, false);
        measurementRepository.save(measurement);
        return new Response(ADDED, true);
    }

    public Response get() {
        List<Measurement> all = measurementRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS,true,byId.get());
    }

    public Response deactivate(Long id) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()) {
            Measurement measurement = byId.get();
            measurement.setActive(false);
            return new Response(DEACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response activate(Long id) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()) {
            Measurement measurement = byId.get();
            measurement.setActive(true);
            return new Response(ACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response delete(Long id) {
        boolean b = measurementRepository.existsById(id);
        if (b) {
            measurementRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, Measurement newMeasurement) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()) {
            Measurement measurement = byId.get();
            String name = newMeasurement.getName();
            if (name != null)
                measurement.setName(name);
            measurementRepository.save(measurement);
            return new Response(EDITED, true);
        }
        return new Response(NOT_FOUND, false);
    }
}
