package com.reda.GestionStock.services.implementation;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.exception.InvalidEntityException;
import com.reda.GestionStock.model.Article;
import com.reda.GestionStock.repository.ArticleRepository;
import com.reda.GestionStock.services.ArticleService;
import com.reda.GestionStock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j // il nous donne un logger (log.)
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    // injection du Repository d'article on fesant injection par constructeur

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository){

        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        // avant de sauvgarder un article on pass par la validation
        List<String> errors = ArticleValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Article is not valid {}", dto);
            throw new InvalidEntityException("L'article n'est pas valid", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(dto)));

    }

    @Override
    public ArticleDto findByID(Integer id) {
        if (id == null){
            log.error("Article ID is null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);

        ArticleDto dto = ArticleDto.fromEntity(article.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException("Auncun Article avec l'id demandé ",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)){
            log.error("code Article is null !! ");
            return null;
        }
        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);

        ArticleDto dto = ArticleDto.fromEntity(article.get());

        return Optional.of(dto).orElseThrow(() ->
                new EntityNotFoundException("Auncun Article avec le code n'ete trouvé demandé ",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public List<ArticleDto> finAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Id Article is null !! ");
            return;
        }
         articleRepository.deleteById(id);
    }
}
