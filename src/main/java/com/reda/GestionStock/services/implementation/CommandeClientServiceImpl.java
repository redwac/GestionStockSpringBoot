package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.CommandeClientDto;
import com.reda.GestionStock.dto.LigneCommandeClientDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.exception.InvalidEntityException;
import com.reda.GestionStock.model.Article;
import com.reda.GestionStock.model.Client;
import com.reda.GestionStock.model.CommandeClient;
import com.reda.GestionStock.model.LigneCommandeClient;
import com.reda.GestionStock.repository.ArticleRepository;
import com.reda.GestionStock.repository.ClientRepository;
import com.reda.GestionStock.repository.CommandeClientRepository;
import com.reda.GestionStock.repository.LigneCommandeClientRepository;
import com.reda.GestionStock.services.CommandeClientService;
import com.reda.GestionStock.validator.CommandeClientValidation;
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
public class CommandeClientServiceImpl implements CommandeClientService {


    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,LigneCommandeClientRepository ligneCommandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidation.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande Client n'est pas valid");
            throw new InvalidEntityException(
                    "Commande Client n'est pas valid", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        // la verification si le client existe
        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("Client with ID {} was not found in DB : ", dto.getClient().getId());
            throw new EntityNotFoundException("le client avec l'id " + dto.getClient().getId() + "n'existe pas", ErrorCodes.CLIENT_NOT_FOUND);
        }
        // la verifivation si l'article existe

        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(newObjetCmdClient -> {
                if (newObjetCmdClient.getArticle().getId() != null) {
                    Optional<Article> article = articleRepository.findById(newObjetCmdClient.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("l'article avec l'id " + newObjetCmdClient.getArticle().getId() + "n'existe pas");
                    }

                }
            });
        }

            if (!articleErrors.isEmpty()) {
                log.warn("Article n'existe pas dans DB : ");
                throw new InvalidEntityException("L'article avec id{} n'existe pas dans la base données ", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
            }
            CommandeClient savedCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(dto));
            // sauvgard et la verivication que cha ligne_commande_client contien id commande_client
            if (dto.getLigneCommandeClients() != null) {

                dto.getLigneCommandeClients().forEach(lignCmdClt -> {
                    LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(lignCmdClt);
                    ligneCommandeClient.setCommandeClient(savedCommandeClient);
                    ligneCommandeClientRepository.save(ligneCommandeClient);
                });
            }


            return CommandeClientDto.fromEntity(savedCommandeClient);

    }
    @Override
    public CommandeClientDto findByID(Integer id) {
        if (id==null){
            log.error("id commande client is null ! ");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Aucune commande clien avec ce id{} n'existe dans la base donnée" + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Code CommandeClient is null ! ");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Aucune commande clien avec le code donné {} n'existe dans la base donnée" + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> finAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("il faut donné l'id à supprimer ! ");
            return;
        }
        commandeClientRepository.deleteById(id);
    }
}
