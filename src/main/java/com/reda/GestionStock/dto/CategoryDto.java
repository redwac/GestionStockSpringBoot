package com.reda.GestionStock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reda.GestionStock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {

    private Integer id;

    private String code;

    private String designation;

    private Integer idEntreprise;

    @JsonIgnore
    private List<ArticleDto> articles ;

    // Category to CategoryDto   // select from data base
    public static CategoryDto fromEntity(Category category){
        if (category == null){
            return null;
            // TODO Exception
        }
        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .idEntreprise(category.getIdEntreprise())
                .build();
    }

    // CategoryDto to Category
    public static Category toEntity(CategoryDto categoryDto){
        if (categoryDto == null){
            return null;
            //TODO Exception
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        category.setIdEntreprise(categoryDto.getIdEntreprise());

        return category;


        /*return Category.builder()
                .code(categoryDto.getCode())
                .designation(categoryDto.getDesignation())
                .build();

         */

    }
}
