package com.reda.GestionStock.Controller.api;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.model.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.reda.GestionStock.Utils.Constants.APP_ROOT;

import java.util.List;

@Api(APP_ROOT + "/articles") // Api pour Swagger
public interface ArticleApi {

    // les DTO sont les intermediaire entre  notre api les l'exterieur
    // pour dir a spring qu'il doit faire une deserialisation(trasformation d'un objet ou d'un texte sous format JSON) il faut ajouter la notation @RequestBody
    @PostMapping(value = APP_ROOT + "/article/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // consume = je vai consommer un format JSON ...
    @ApiOperation(value = "Enregistrer un article ", notes = "(ajouter / modisier)", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet article n est pas valid")
    })
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT + "article/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recherche un article par OD", notes = "cette methode permet de chercher article par ID)", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'article a ete trouve"),
            @ApiResponse(code = 404, message = "aucun article n'existe dans la base données")
    })
    Article findByID(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "article/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recherche un article par code ", notes = "cette methode permet de chercher article par CODE)", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'article a ete trouve"),
            @ApiResponse(code = 404, message = "aucun article n'existe dans la base données")
    })
    ArticleDto findByCodeArticle(@PathVariable("{codeArticle}") String codeArticle);

    @GetMapping(value = APP_ROOT + "article/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la lise des article  ", notes = "cette methode permet de revoyer la liste de tous les articles", responseContainer = "List <ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste articles/ Liste vide")
    })
    List<ArticleDto> finAll();

    @DeleteMapping(value = APP_ROOT + "article/delete/{idArticle}")
    @ApiOperation(value = "supprimer un article  ", notes = "cette methode permet de supprimer un article par ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete supprimer'")
    })
    void delete(@PathVariable("{idArticle}") Integer id);
}
