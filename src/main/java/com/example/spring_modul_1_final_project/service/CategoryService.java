package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.utils.Messages;
import com.example.spring_modul_1_final_project.entity.Category;
import com.example.spring_modul_1_final_project.payload.CategoryDto;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements Messages {
    private final CategoryRepository categoryRepository;

    public Response add(CategoryDto categoryDto) {
        String name = categoryDto.getName();
        boolean b = categoryRepository.existsByName(name);
        if (b) return new Response(EXIST, false);
        Category category = new Category();
        category.setName(name);

        Long parentCategoryId = categoryDto.getParentCategoryId();
        if (parentCategoryId != null) {
            Optional<Category> byId = categoryRepository.findById(parentCategoryId);
            if (byId.isEmpty()) return new Response(PARENT_CATEGORY_NOT_FOUND, false);
            category.setParentCategory(byId.get());
        }
        categoryRepository.save(category);
        return new Response(ADDED, true);
    }

    public Response get() {
        List<Category> all = categoryRepository.findAll();
        return new Response(SUCCESS, true, all);
    }

    public Response getById(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) return new Response(NOT_FOUND, false);
        return new Response(SUCCESS,true,byId.get());
    }

    public Response deactivate(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            category.setActive(false);
            return new Response(DEACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response activate(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            category.setActive(true);
            return new Response(ACTIVATED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response delete(Long id) {
        boolean b = categoryRepository.existsById(id);
        if (b) {
            categoryRepository.deleteById(id);
            return new Response(DELETED, true);
        }
        return new Response(NOT_FOUND, false);
    }

    public Response edit(Long id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            Long parentCategoryId = categoryDto.getParentCategoryId();
            Optional<Category> optionalParent = categoryRepository.findById(parentCategoryId);
            if (optionalParent.isEmpty()) return new Response(PARENT_CATEGORY_NOT_FOUND, false);
            category.setParentCategory(optionalParent.get());
            if (categoryDto.getName() != null) category.setName(categoryDto.getName());
            categoryRepository.save(category);
            return new Response(EDITED, true);
        }
        return new Response(CATEGORY_NOT_FOUND, false);
    }
}
