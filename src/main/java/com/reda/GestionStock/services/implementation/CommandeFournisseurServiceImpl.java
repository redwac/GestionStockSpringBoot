package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.CommandeFournissuerDto;
import com.reda.GestionStock.dto.LigneCommandeFournisseurDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.exception.InvalidEntityException;
import com.reda.GestionStock.model.*;
import com.reda.GestionStock.repository.*;
import com.reda.GestionStock.services.CommandeFournisseurService;
import com.reda.GestionStock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, FournisseurRepository fournisseurRepository, ArticleRepository articleRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
    }



    @Override
    public CommandeFournissuerDto save(CommandeFournissuerDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande Fournisseur n'est pas valide ");
            throw new InvalidEntityException(
                    "Commande Fournisseur n'est pas valid", ErrorCodes.COMMANDE_FOURNISSEUR_FOUND, errors);
        }

        // la verification si le fournisseur existe
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} was not found in DB : ", dto.getFournisseur().getId());
            throw new EntityNotFoundException("le fournisseur avec l'id " + dto.getFournisseur().getId() + "n'existe pas", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        // la verifivation si l'article existe

        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(newObjetCmdFournisseur -> {
                if (newObjetCmdFournisseur.getArticle().getId() != null) {
                    Optional<Article> article = articleRepository.findById(newObjetCmdFournisseur.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("l'article avec l'id " + newObjetCmdFournisseur.getArticle().getId() + "n'existe pas");
                    }

                }
            });
        }
            if (!articleErrors.isEmpty()) {
                log.warn("Article n'existe pas dans DB : ");
                throw new InvalidEntityException("L'article avec id{} n'existe pas dans la base données ", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
            }

            CommandeFournissuer savedCommandeFournisseur = commandeFournisseurRepository.save(CommandeFournissuerDto.toEntity(dto));
            // sauvgard et la verivication que cha ligne_commande_fournisseur contien id commande_fournisseur
            if (dto.getLigneCommandeFournisseurs() != null) {

                dto.getLigneCommandeFournisseurs().forEach(lignCmdFournissur -> {
                    LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(lignCmdFournissur);
                    ligneCommandeFournisseur.setCommandeFournissuer(savedCommandeFournisseur);
                    ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
                });
            }


            return CommandeFournissuerDto.fromEntity(savedCommandeFournisseur);
    }




    @Override
    public CommandeFournissuerDto findByID(Integer id) {
        if (id==null){
            log.error("id CommandeFournisseur is null ! ");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournissuerDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Aucune commande cournisseur avec ce id{} n'existe dans la base donnée" + id, ErrorCodes.COMMANDE_FOURNISSEUR_FOUND
                ));
    }

    @Override
    public CommandeFournissuerDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Code CommandeFournisseur is null ! ");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournissuerDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Aucune commande fournisseur avec le code donné {} n'existe dans la base donnée" + code, ErrorCodes.COMMANDE_FOURNISSEUR_FOUND
                ));
    }

    @Override
    public List<CommandeFournissuerDto> finAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournissuerDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("il faut donné l'id à supprimer ! ");
            return;
        }
        commandeFournisseurRepository.deleteById(id);
    }

}
