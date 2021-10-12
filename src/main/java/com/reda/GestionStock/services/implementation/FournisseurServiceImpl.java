package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.ClientDto;
import com.reda.GestionStock.dto.FournisseurDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.exception.InvalidEntityException;
import com.reda.GestionStock.repository.FournisseurRepository;
import com.reda.GestionStock.services.FournisseurService;
import com.reda.GestionStock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Le Fournisseur n'est pas valid ! ");
            throw new InvalidEntityException("Fournisseur n'est pas valid 2 !!! ", ErrorCodes.FOURNISSEUR_NOT_FOUND, errors);
        }
        return FournisseurDto.fromEntity(
                fournisseurRepository.save(FournisseurDto.toEntity(dto))
        );
    }

    @Override
    public FournisseurDto findByID(Integer id) {
        if (id == null){
            log.error("Id Fournisseur is null ! ");
            return null;
        }
        return fournisseurRepository.findById(id)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Fournisseur n'est pas valid", ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<FournisseurDto> finAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id== null){
            log.error("Id to delete fournisseur is null");
            return;
        }
        fournisseurRepository.deleteById(id);

    }
}
