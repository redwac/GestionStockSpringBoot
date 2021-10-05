package com.reda.GestionStock.handlers;

import com.reda.GestionStock.exception.ErrorCodes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;

    private ErrorCodes errorCodes;

    private String message;

    private List<String> errorsList;

}
