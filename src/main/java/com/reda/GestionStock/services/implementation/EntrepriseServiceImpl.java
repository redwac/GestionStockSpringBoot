package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.EntrepriseDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.exception.InvalidEntityException;
import com.reda.GestionStock.repository.EntrepriseRepository;
import com.reda.GestionStock.services.EntrepriseService;
import com.reda.GestionStock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;


    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Entreprise is not valid !! ");
            throw new InvalidEntityException(
                    "Entreprise is not Valid 2 !!",
                    ErrorCodes.ENTREPRISE_NOT_FOUND, errors );
        }
        return EntrepriseDto.fromEntity(
                entrepriseRepository.save(
                        EntrepriseDto.toEntity(dto)
                )
        );
    }

    @Override
    public EntrepriseDto findByID(Integer id) {
        if (id == null){
            log.error("Id is null");
            return null;
        }
        return entrepriseRepository.findById(id)
                .map(EntrepriseDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune entreprise avec ce ID n'existe !!", ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> finAll() {

        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Id Entreprise is null !! ");
            return;
        }
        entrepriseRepository.deleteById(id);

    }
}
