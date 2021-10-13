package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.EntrepriseDto;
import com.reda.GestionStock.dto.RoleDto;
import com.reda.GestionStock.dto.UtilisateurDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.exception.InvalidEntityException;
import com.reda.GestionStock.repository.EntrepriseRepository;
import com.reda.GestionStock.repository.RoleRepository;
import com.reda.GestionStock.services.EntrepriseService;
import com.reda.GestionStock.services.UtilisateurService;
import com.reda.GestionStock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    private RoleRepository roleRepository;
    private UtilisateurService utilisateurService;


    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, RoleRepository roleRepository, UtilisateurService utilisateurService) {
        this.entrepriseRepository = entrepriseRepository;
        this.roleRepository = roleRepository;
        this.utilisateurService = utilisateurService;
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

        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(dto))
        );

        UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

        UtilisateurDto savedUser = utilisateurService.save(utilisateur);

        RoleDto rolesDto = RoleDto.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();

        roleRepository.save(RoleDto.toEntity(rolesDto));

        return  savedEntreprise;

    }

    private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
        return UtilisateurDto.builder()
                .adress(dto.getAdress())
                .nom(dto.getNom())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .motDePass(generateRandomPassword())
                .entreprise(dto)
                .dateNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build();
    }

    private String generateRandomPassword() {
        return "som3R@nd0mP@$$word";
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
