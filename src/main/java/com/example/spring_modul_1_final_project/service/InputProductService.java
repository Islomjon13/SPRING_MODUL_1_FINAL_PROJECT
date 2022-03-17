package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.Input;
import com.example.spring_modul_1_final_project.entity.InputProduct;
import com.example.spring_modul_1_final_project.entity.Product;
import com.example.spring_modul_1_final_project.payload.InputProductDto;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.InputProductRepository;
import com.example.spring_modul_1_final_project.repository.InputRepository;
import com.example.spring_modul_1_final_project.repository.ProductRepository;
import com.example.spring_modul_1_final_project.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputProductService implements Messages {
    private final InputProductRepository inputProductRepository;
    private final ProductRepository productRepository;
    private final InputRepository inputRepository;

    public Response add(InputProductDto inputProductDto) {
        Long inputId = inputProductDto.getInputId();
        Optional<Input> optionalInput = inputRepository.findById(inputId);
        if (optionalInput.isEmpty()) return new Response(INPUT_NOT_FOUND, false);
        Long productId = inputProductDto.getProductId();
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) return new Response(PRODUCT_NOT_FOUND, false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        return new Response(ADDED, true);
    }

    public Response get() {
        List<InputProduct> all = inputProductRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS, true, byId.get());
    }

    public Response delete(Long id) {
        boolean b = inputProductRepository.existsById(id);
        if (b) {
            inputProductRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, InputProductDto inputProductDto) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()) return new Response(INPUT_PRODUCT_NOT_FOUND, false);
        InputProduct inputProduct = optionalInputProduct.get();

        Long inputId = inputProductDto.getInputId();
        Optional<Input> optionalInput = inputRepository.findById(inputId);
        optionalInput.ifPresent(inputProduct::setInput);

        Long productId = inputProductDto.getProductId();
        Optional<Product> optionalProduct = productRepository.findById(productId);
        optionalProduct.ifPresent(inputProduct::setProduct);

        Double amount = inputProductDto.getAmount();
        if (amount != null) inputProduct.setAmount(amount);

        Double price = inputProductDto.getPrice();
        if (price != null) inputProduct.setPrice(price);

        Timestamp expireDate = inputProduct.getExpireDate();
        if (expireDate != null) inputProduct.setExpireDate(expireDate);
        return new Response(EDITED, true);
    }
}
