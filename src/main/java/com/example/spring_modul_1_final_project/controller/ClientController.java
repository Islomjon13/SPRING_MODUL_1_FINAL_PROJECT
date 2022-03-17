package com.example.spring_modul_1_final_project.controller;

import com.example.spring_modul_1_final_project.entity.Client;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/add")
    public Response add(@RequestBody Client client) {
        return clientService.add(client);
    }

    @GetMapping("/get")
    public Response get() {
        return clientService.get();
    }

    @GetMapping("/get/{id}")
    public Response getById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return clientService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public Response edit(@PathVariable Long id, @RequestBody Client client) {
        return clientService.edit(id, client);
    }
}
