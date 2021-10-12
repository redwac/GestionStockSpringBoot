package com.reda.GestionStock.Controller;

import com.reda.GestionStock.Controller.api.CategoryApi;
import com.reda.GestionStock.dto.CategoryDto;
import com.reda.GestionStock.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryController implements CategoryApi {


    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        return categoryService.save(dto);
    }

    @Override
    public CategoryDto findByID(Integer id) {
        return categoryService.findByID(id);
    }

    @Override
    public CategoryDto findByCode(String code) {
        return categoryService.findByCode(code);
    }

    @Override
    public List<CategoryDto> finAll() {
        return finAll();
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }
}
