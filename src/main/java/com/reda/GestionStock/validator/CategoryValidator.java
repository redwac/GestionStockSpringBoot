package com.reda.GestionStock.validator;

import com.reda.GestionStock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto categoryDto){
        List<String> errors = new ArrayList<>();

        if(!StringUtils.hasLength(categoryDto.getCode())){
            errors.add("Veuiller renseigner le code de la category ");
        }

        return errors;
    }
}
