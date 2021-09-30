package com.reda.GestionStock.validator;

import com.reda.GestionStock.dto.MvStkDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MvStkValidator {

    public static List<String> validate(MvStkDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuillez renseigner la date du mouvenent");
            errors.add("Veuillez renseigner la quantite du mouvenent");
            errors.add("Veuillez renseigner l'article");
            errors.add("Veuillez renseigner le type du mouvement");

            return errors;
        }
        if (dto.getDateMvt() == null) {
            errors.add("Veuillez renseigner la date du mouvenent");
        }
        if (dto.getQuantite() == null || dto.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
            errors.add("Veuillez renseigner la quantite du mouvenent");
        }
        if (dto.getArticle() == null || dto.getArticle().getId() == null) {
            errors.add("Veuillez renseigner l'article");
        }
        if (!StringUtils.hasLength(dto.getTypeMvtStock().name())) {
            errors.add("Veuillez renseigner le type du mouvement");
        }

        return errors;
    }
}
