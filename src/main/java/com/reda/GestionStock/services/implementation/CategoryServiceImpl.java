package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.CategoryDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.exception.InvalidEntityException;
import com.reda.GestionStock.model.Category;
import com.reda.GestionStock.repository.CategoryRepository;
import com.reda.GestionStock.services.CategoryService;
import com.reda.GestionStock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;


    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("la category n'est pas valid");
            throw new InvalidEntityException("la category n'es pas valid ", ErrorCodes.CATEGORY_NOT_FOUND, errors);

        }
        return CategoryDto.fromEntity(
                categoryRepository.save(
                        CategoryDto.toEntity(dto)
                )
        );
    }

    @Override
    public CategoryDto findByID(Integer id) {
        if (id == null){
            log.error("Id category is null");
            return null;
        }
        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Auncune category avec id n'existe", ErrorCodes.CATEGORY_NOT_FOUND
                ));

    }

    @Override
    public CategoryDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("le code category est null");
            return null;
        }

        return categoryRepository.findCategoryByCode(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Auncune category n'existe avec ce code ",
                        ErrorCodes.CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public List<CategoryDto> finAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("aucune category avec l'Id demandé ");
            return ;
        }
        categoryRepository.deleteById(id);
    }

}
