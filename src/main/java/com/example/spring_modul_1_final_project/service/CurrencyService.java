package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.Currency;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.CurrencyRepository;
import com.example.spring_modul_1_final_project.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService implements Messages {
    private final CurrencyRepository currencyRepository;

    public Response add(Currency currency) {
        boolean b = currencyRepository.existsByName(currency.getName());
        if (b)
            return new Response(EXIST, false);
        currencyRepository.save(currency);
        return new Response(ADDED, true);
    }

    public Response get() {
        List<Currency> all = currencyRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS, true, byId.get());
    }

    public Response deactivate(Long id) {
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()) {
            Currency currency = byId.get();
            currency.setActive(false);
            return new Response(DEACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response activate(Long id) {
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()) {
            Currency currency = byId.get();
            currency.setActive(true);
            return new Response(ACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response delete(Long id) {
        boolean b = currencyRepository.existsById(id);
        if (b) {
            currencyRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, Currency newCurrency) {
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()) {
            Currency currency = byId.get();
            String name = newCurrency.getName();
            if (name != null)
                currency.setName(name);
            currencyRepository.save(currency);
            return new Response(EDITED, true);
        }
        return new Response(NOT_FOUND, false);
    }
}
