package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.Client;
import com.example.spring_modul_1_final_project.entity.Currency;
import com.example.spring_modul_1_final_project.entity.Output;
import com.example.spring_modul_1_final_project.entity.WareHouse;
import com.example.spring_modul_1_final_project.payload.OutputDto;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.*;
import com.example.spring_modul_1_final_project.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutputService implements Messages {
    private final OutputRepository outputRepository;
    private final WareHouseRepository wareHouseRepository;
    private final CurrencyRepository currencyRepository;
    private final ClientRepository clientRepository;

    public Response add(OutputDto outputDto) {
        Long wareHouseId = outputDto.getWareHouseId();
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(wareHouseId);
        if (optionalWareHouse.isEmpty()) return new Response(WARE_HOUSE_NOT_FOUND, false);
        Long currencyId = outputDto.getCurrencyId();
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if (optionalCurrency.isEmpty()) return new Response(CURRENCY_NOT_FOUND, false);
        Long clientId = outputDto.getClientId();
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isEmpty()) return new Response(CLIENT_NOT_FOUND, false);

        Output output = new Output();
        output.setWareHouse(optionalWareHouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());

        output.setDate(outputDto.getDate());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new Response(ADDED, true);
    }

    public Response get() {
        List<Output> all = outputRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<Output> byId = outputRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS, true, byId.get());
    }

    public Response delete(Long id) {
        boolean b = outputRepository.existsById(id);
        if (b) {
            outputRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()) return new Response(OUTPUT_NOT_FOUND, false);
        Output output = optionalOutput.get();

        Long wareHouseId = outputDto.getWareHouseId();
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(wareHouseId);
        optionalWareHouse.ifPresent(output::setWareHouse);

        Long currencyId = outputDto.getCurrencyId();
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        optionalCurrency.ifPresent(output::setCurrency);

        Long clientId = outputDto.getClientId();
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        optionalClient.ifPresent(output::setClient);

        Timestamp date = outputDto.getDate();
        if (date != null) output.setDate(date);
        Integer factureNumber = output.getFactureNumber();
        if (factureNumber != null) output.setFactureNumber(factureNumber);
        outputRepository.save(output);
        return new Response(EDITED,true);
    }
}
