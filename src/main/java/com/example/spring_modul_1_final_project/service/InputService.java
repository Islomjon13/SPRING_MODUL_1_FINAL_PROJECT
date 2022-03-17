package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.Supplier;
import com.example.spring_modul_1_final_project.entity.Currency;
import com.example.spring_modul_1_final_project.entity.Input;
import com.example.spring_modul_1_final_project.entity.WareHouse;
import com.example.spring_modul_1_final_project.payload.InputDto;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.SupplierRepository;
import com.example.spring_modul_1_final_project.repository.CurrencyRepository;
import com.example.spring_modul_1_final_project.repository.InputRepository;
import com.example.spring_modul_1_final_project.repository.WareHouseRepository;
import com.example.spring_modul_1_final_project.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputService implements Messages {
    private final InputRepository inputRepository;
    private final WareHouseRepository wareHouseRepository;
    private final CurrencyRepository currencyRepository;
    private final SupplierRepository supplierRepository;

    public Response add(InputDto inputDto) {
        Long wareHouseId = inputDto.getWareHouseId();
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(wareHouseId);
        if (optionalWareHouse.isEmpty()) return new Response(WARE_HOUSE_NOT_FOUND, false);

        Long currencyId = inputDto.getCurrencyId();
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if (optionalCurrency.isEmpty()) return new Response(CURRENCY_NOT_FOUND, false);

        Long supplierId = inputDto.getSupplierId();
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (optionalSupplier.isEmpty()) return new Response(CLIENT_NOT_FOUND, false);

        Input input = new Input();
        input.setWareHouse(optionalWareHouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());

        input.setDate(inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepository.save(input);
        return new Response(ADDED, true);
    }

    public Response get() {
        List<Input> all = inputRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<Input> byId = inputRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS, true, byId.get());
    }

    public Response delete(Long id) {
        boolean b = inputRepository.existsById(id);
        if (b) {
            inputRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty()) return new Response(INPUT_NOT_FOUND, false);
        Input input = optionalInput.get();

        Long wareHouseId = inputDto.getWareHouseId();
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(wareHouseId);
        optionalWareHouse.ifPresent(input::setWareHouse);

        Long currencyId = inputDto.getCurrencyId();
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        optionalCurrency.ifPresent(input::setCurrency);

        Long supplierId = inputDto.getSupplierId();
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        optionalSupplier.ifPresent(input::setSupplier);

        Timestamp date = inputDto.getDate();
        if (date != null) input.setDate(date);
        Integer factureNumber = input.getFactureNumber();
        if (factureNumber != null) input.setFactureNumber(factureNumber);
        inputRepository.save(input);
        return new Response(EDITED,true);
    }
}
