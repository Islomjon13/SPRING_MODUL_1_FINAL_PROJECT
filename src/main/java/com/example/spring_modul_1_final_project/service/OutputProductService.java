package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.Output;
import com.example.spring_modul_1_final_project.entity.OutputProduct;
import com.example.spring_modul_1_final_project.entity.Product;
import com.example.spring_modul_1_final_project.payload.OutputProductDto;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.OutputProductRepository;
import com.example.spring_modul_1_final_project.repository.OutputRepository;
import com.example.spring_modul_1_final_project.repository.ProductRepository;
import com.example.spring_modul_1_final_project.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutputProductService implements Messages {
    private final OutputProductRepository outputProductRepository;
    private final ProductRepository productRepository;
    private final OutputRepository outputRepository;

    public Response add(OutputProductDto outputProductDto) {
        Long outputId = outputProductDto.getOutputId();
        Optional<Output> optionalOutput = outputRepository.findById(outputId);
        if (optionalOutput.isEmpty()) return new Response(OUTPUT_NOT_FOUND, false);
        Long productId = outputProductDto.getProductId();
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) return new Response(PRODUCT_NOT_FOUND, false);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        return new Response(ADDED, true);
    }

    public Response get() {
        List<OutputProduct> all = outputProductRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<OutputProduct> byId = outputProductRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS, true, byId.get());
    }

    public Response delete(Long id) {
        boolean b = outputProductRepository.existsById(id);
        if (b) {
            outputProductRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isEmpty()) return new Response(INPUT_PRODUCT_NOT_FOUND, false);
        OutputProduct outputProduct = optionalOutputProduct.get();

        Long outputId = outputProductDto.getOutputId();
        Optional<Output> optionalOutput = outputRepository.findById(outputId);
        optionalOutput.ifPresent(outputProduct::setOutput);

        Long productId = outputProductDto.getProductId();
        Optional<Product> optionalProduct = productRepository.findById(productId);
        optionalProduct.ifPresent(outputProduct::setProduct);

        Double amount = outputProductDto.getAmount();
        if (amount != null) outputProduct.setAmount(amount);

        Double price = outputProductDto.getPrice();
        if (price != null) outputProduct.setPrice(price);

        return new Response(EDITED, true);
    }
}
