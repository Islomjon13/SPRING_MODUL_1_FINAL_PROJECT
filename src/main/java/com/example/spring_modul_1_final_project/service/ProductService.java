package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.AttachmentCollection;
import com.example.spring_modul_1_final_project.entity.Category;
import com.example.spring_modul_1_final_project.entity.Measurement;
import com.example.spring_modul_1_final_project.repository.AttachmentCollectionRepository;
import com.example.spring_modul_1_final_project.repository.CategoryRepository;
import com.example.spring_modul_1_final_project.repository.MeasurementRepository;
import com.example.spring_modul_1_final_project.utils.Messages;
import com.example.spring_modul_1_final_project.entity.Product;
import com.example.spring_modul_1_final_project.payload.ProductDto;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements Messages {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MeasurementRepository measurementRepository;
    private final AttachmentCollectionRepository attachmentCollectionRepository;


    public Response add(ProductDto productDto) {
        String name = productDto.getName();
        Long categoryId = productDto.getCategoryId();
        Long measurementId = productDto.getMeasurementId();
        Long attachmentCollectionId = productDto.getAttachmentCollectionId();

        boolean exists = productRepository.existsByNameAndCategoryId(name, categoryId);
        if (exists) return new Response(EXIST, false);

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) return new Response(CATEGORY_NOT_FOUND, false);

        Optional<Measurement> measurementOptional = measurementRepository.findById(measurementId);
        if (measurementOptional.isEmpty()) return new Response(MEASUREMENT_NOT_FOUND, false);

        Optional<AttachmentCollection> attachmentCollectionOptional = attachmentCollectionRepository.findById(attachmentCollectionId);
        if (attachmentCollectionOptional.isEmpty()) return new Response(ATTACHMENT_NOT_FOUND, false);

        Product product = new Product();
        product.setName(name);
        product.setCategory(categoryOptional.get());
        product.setMeasurement(measurementOptional.get());
        product.setAttachmentCollection(attachmentCollectionOptional.get());
        productRepository.save(product);
        return new Response(ADDED, true);
    }

    public Response get() {
        List<Product> all = productRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS, true, byId.get());
    }

    public Response deactivate(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setActive(false);
            return new Response(DEACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response activate(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setActive(true);
            return new Response(ACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response delete(Long id) {
        boolean b = productRepository.existsById(id);
        if (b) {
            productRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) return new Response(PRODUCT_NOT_FOUND, false);
        Product product = optionalProduct.get();

        String name = productDto.getName();
        Long categoryId = productDto.getCategoryId();
        Long measurementId = productDto.getMeasurementId();
        Long attachmentCollectionId = productDto.getAttachmentCollectionId();

        if (categoryId != null) {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if (categoryOptional.isEmpty()) return new Response(CATEGORY_NOT_FOUND, false);
            product.setCategory(categoryOptional.get());
        }
        if (measurementId != null) {
            Optional<Measurement> measurementOptional = measurementRepository.findById(measurementId);
            if (measurementOptional.isEmpty()) return new Response(MEASUREMENT_NOT_FOUND, false);
            product.setMeasurement(measurementOptional.get());
        }

        if (attachmentCollectionId != null) {
            Optional<AttachmentCollection> attachmentCollectionOptional = attachmentCollectionRepository.findById(attachmentCollectionId);
            if (attachmentCollectionOptional.isEmpty()) return new Response(ATTACHMENT_NOT_FOUND, false);
            product.setAttachmentCollection(attachmentCollectionOptional.get());
        }

        if (name != null)
            product.setName(name);

        productRepository.save(product);
        return new Response(EDITED, true);
    }
}
