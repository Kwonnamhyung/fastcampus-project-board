package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType type , String searchKeyword) {

        return Page.empty();
    };

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(Long articleId) {
        return ArticleDto.of(LocalDateTime.now() , "KNH" , "TEST" , "TESTCONTENT" , "TEST");
    }

    public void saveArticle(ArticleDto dto) {

    }

    public void updateArticle(Long articleId , ArticleUpdateDto dto){

    }


    public void deleteArticle(Long ArticleId) {
    }
}
