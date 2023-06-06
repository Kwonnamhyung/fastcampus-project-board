package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto.Response> searchArticles(SearchType type , String searchKeyword , Pageable pageable) {


        System.out.println("pageable = " + pageable);
        if(searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(Article::entityToResponse);
        }

        return switch (type) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword , pageable).map(Article::entityToResponse);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword , pageable).map(Article::entityToResponse);
            case ID -> articleRepository.findByUserAccount_IdContaining(searchKeyword , pageable).map(Article::entityToResponse);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword , pageable).map(Article::entityToResponse);
            case HASHTAG -> articleRepository.findByHashtag("#" + searchKeyword , pageable).map(Article::entityToResponse);
        };
    };

    @Transactional(readOnly = true)
    public ArticleDto.ResponseWithComment searchArticle(Long articleId) {

        Optional<Article> optional = articleRepository.findById(articleId);

        return Article.entityToResponseWithComment(optional.orElseThrow(() -> new EntityNotFoundException("조회된 게시글이 없습니다")));
    }

    public void saveArticle(ArticleDto.Request request) {

        articleRepository.save(Article.toEntity(request));

    }

    public void updateArticle(Long articleId , ArticleUpdateDto dto){
        Article article = articleRepository.getReferenceById(articleId);
        if(dto.getTitle() != null) {
            article.setTitle(dto.getTitle());
        }

        if(dto.getContent() != null) {
            article.setContent(dto.getContent());
        }

        article.setHashtag(dto.getHashtag());
    }


    public void deleteArticle(Long articleId) {

        articleRepository.deleteById(articleId);
    }
}
