package com.reda.GestionStock.validator;

import com.reda.GestionStock.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VentesValidator {

    public static List<String> validate(VentesDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuillez renseigner le code du vente");
            errors.add("Veuillez renseigner la date du vente ");
            return errors;
        }
        if (!StringUtils.hasLength(dto.getCode())) {
            errors.add("Veuillez renseigner le code du vente");
        }
        if (dto.getDateVente() == null ) {
            errors.add("Veuillez renseigner la date du vente ");
        }
        if (!StringUtils.hasLength(dto.getCommantaire())) {
            errors.add("Veuillez renseigner le commantaire pour cette vente ");
        }

        return errors;
    }
}
