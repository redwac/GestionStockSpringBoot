package com.reda.GestionStock.Controller.api;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.reda.GestionStock.Utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories") // Api pour Swagger
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // consume = je vai consommer un format JSON ...
    @ApiOperation(value = "Enregistrer une category ", notes = "cette methode permet d'enregistrer ou de modifier une category",
            response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet category cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet category n est pas valid")
    })
    CategoryDto save(@RequestBody CategoryDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    @GetMapping(value = APP_ROOT + "categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recherche une category par ID", notes = "cette methode permet de chercher un category par ID)",
            response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la category a ete trouve"),
            @ApiResponse(code = 404, message = "aucune category n'existe dans la base données avec l id fourni")
    })
    CategoryDto findByID(@PathVariable("{idCategory}") Integer id);

    @GetMapping(value = APP_ROOT + "categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recherche une category par code ", notes = "cette methode permet de chercher une category par CODE)",
            response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "la category a ete trouve"),
            @ApiResponse(code = 404, message = "aucune category n'existe dans la base données avec l id fourni")
    })
    CategoryDto findByCode(@PathVariable("{codeCategory}") String code);

    @GetMapping(value = APP_ROOT + "categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la lise des categories  ", notes = "cette methode permet de revoyer la liste de tous les categories",
            responseContainer = "List <CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste category/ Liste vide")
    })
    List<CategoryDto> finAll();

    @DeleteMapping(value = APP_ROOT + "categories/delete/{idCategory}")
    @ApiOperation(value = "supprimer une category  ", notes = "cette methode permet de supprimer une category par ID",
            response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La category a ete supprimer'")
    })
    void delete(@PathVariable("{idCategory}")Integer id);
}
