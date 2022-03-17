package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.Client;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.ClientRepository;
import com.example.spring_modul_1_final_project.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements Messages {
    private final ClientRepository clientRepository;

    public Response add(Client client) {
        boolean b = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (b)
            return new Response(EXIST, false);
        clientRepository.save(client);
        return new Response(ADDED, true);
    }

    public Response get() {
        List<Client> all = clientRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS, true, byId.get());
    }

    public Response delete(Long id) {
        boolean b = clientRepository.existsById(id);
        if (b) {
            clientRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, Client newClient) {
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()) {
            Client client = byId.get();
            String name = newClient.getName();
            String phoneNumber = newClient.getPhoneNumber();
            if (name != null)
                client.setName(name);
            if (phoneNumber != null)
                client.setPhoneNumber(phoneNumber);
            clientRepository.save(client);
            return new Response(EDITED, true);
        }
        return new Response(NOT_FOUND, false);
    }
}
