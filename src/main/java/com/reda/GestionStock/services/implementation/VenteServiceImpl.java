package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.CommandeClientDto;
import com.reda.GestionStock.dto.CommandeFournissuerDto;
import com.reda.GestionStock.dto.LigneVentDto;
import com.reda.GestionStock.dto.VentesDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.exception.InvalidEntityException;
import com.reda.GestionStock.model.Article;
import com.reda.GestionStock.model.LigneVente;
import com.reda.GestionStock.model.Ventes;
import com.reda.GestionStock.repository.ArticleRepository;
import com.reda.GestionStock.repository.LigneVenteRepository;
import com.reda.GestionStock.repository.VenteRepository;
import com.reda.GestionStock.services.VenteService;
import com.reda.GestionStock.validator.VentesValidator;
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
public class VenteServiceImpl implements VenteService {

    private ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;
    private VenteRepository venteRepository;

    @Autowired
    public VenteServiceImpl(ArticleRepository articleRepository, LigneVenteRepository ligneVenteRepository, VenteRepository venteRepository) {
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.venteRepository = venteRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {

        List<String> errors = VentesValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Ventes n'est pas valid");
            throw new InvalidEntityException("Vante n'est pas valid 2", ErrorCodes.VENTES_NOT_FOUND, errors);
        }
        List<String> articleErrors = new ArrayList<>();
        dto.getLigneVents().forEach(ligneVentDto -> {
            Optional<Article> article = articleRepository.findById(ligneVentDto.getArticleDto().getId());
            if (article.isEmpty()){
                articleErrors.add("Aucun acticle avec id{} n a ete trouvé dans la base données");
            }
        });
        if (!articleErrors.isEmpty()){
            log.error("one or more article not found on databases ", errors);
            throw new InvalidEntityException("un ou plusieur acticle n'ont pas ete trouvé dans la base doonée", ErrorCodes.VENTES_NOT_FOUND, errors);
        }

        //si tous va bien sans erreur on sauvegard la vente
        Ventes saveVente = venteRepository.save(VentesDto.toEntity(dto));

        dto.getLigneVents().forEach(ligneVentDto -> {
            LigneVente ligneVente = LigneVentDto.toEntity(ligneVentDto);
            ligneVente.setVente(saveVente);
            ligneVenteRepository.save(ligneVente);
        });
        return VentesDto.fromEntity(saveVente);
    }

    @Override
    public VentesDto findByID(Integer id) {
        if (id==null){
            log.error("id vente is null ! ");
            return null;
        }
        return venteRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Aucune vente avec ce id{} n'existe dans la base donnée" + id, ErrorCodes.COMMANDE_FOURNISSEUR_FOUND
                ));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Code CommandeClient is null ! ");
            return null;
        }
        return venteRepository.findVenteByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "Aucune commande clien avec le code donné {} n'existe dans la base donnée" + code, ErrorCodes.VENTES_NOT_FOUND
                ));
    }

    @Override
    public List<VentesDto> finAll() {
        return venteRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("il faut donné l'id à supprimer ! ");
            return;
        }
        venteRepository.deleteById(id);
    }
}
