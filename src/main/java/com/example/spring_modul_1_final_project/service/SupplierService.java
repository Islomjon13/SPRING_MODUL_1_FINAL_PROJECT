package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.Supplier;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.SupplierRepository;
import com.example.spring_modul_1_final_project.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService implements Messages {
    private final SupplierRepository supplierRepository;

    public Response add(Supplier supplier) {
        boolean b = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (b)
            return new Response(EXIST, false);
        supplierRepository.save(supplier);
        return new Response(ADDED, true);
    }

    public Response get() {
        List<Supplier> all = supplierRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS, true, byId.get());
    }

    public Response deactivate(Long id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isPresent()) {
            Supplier supplier = byId.get();
            supplier.setActive(false);
            return new Response(DEACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response activate(Long id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isPresent()) {
            Supplier supplier = byId.get();
            supplier.setActive(true);
            return new Response(ACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response delete(Long id) {
        boolean b = supplierRepository.existsById(id);
        if (b) {
            supplierRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, Supplier newSupplier) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isPresent()) {
            Supplier supplier = byId.get();
            String name = newSupplier.getName();
            String phoneNumber = newSupplier.getPhoneNumber();
            if (name != null)
                supplier.setName(name);
            if (phoneNumber != null)
                supplier.setPhoneNumber(phoneNumber);
            supplierRepository.save(supplier);
            return new Response(EDITED, true);
        }
        return new Response(NOT_FOUND, false);
    }
}
