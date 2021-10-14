package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.CategoryDto;
import com.reda.GestionStock.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)// pour dir a spring qu'on va generer des testes unitaire
@SpringBootTest// lorsqu'on genere un Autoweird spring va preparer le contexte  pour  test
class CategoryServiceImplTest {
    // les methodes de teste sont tous de type void

    @Autowired
    public CategoryService service;
    @Test
    public void sholdSaveCategoryWhithSuccess(){
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("code Teste")
                .designation("Des Test")
                .idEntreprise(1)
                .build();
        CategoryDto expectedService = service.save(expectedCategory);
    }

}