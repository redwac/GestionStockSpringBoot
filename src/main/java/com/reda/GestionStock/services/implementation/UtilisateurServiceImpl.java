package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.dto.UtilisateurDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.exception.InvalidEntityException;
import com.reda.GestionStock.repository.UtilisateurRepository;
import com.reda.GestionStock.services.UtilisateurService;
import com.reda.GestionStock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("User is not valid !!");
            throw new InvalidEntityException("User is not Valis 2 !! ", ErrorCodes.UTILISATEUR_NOT_FOUND, errors);
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(UtilisateurDto.toEntity(dto))
        );
    }

    @Override
    public UtilisateurDto findByID(Integer id) {
        if (id == null){
            log.error("Id User is null");
            return null;
        }
        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Utilisateur n'est pas valid", ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> finAll() {

        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Id User to delete is null ! ");
            return;
        }
        utilisateurRepository.deleteById(id);

    }
}
