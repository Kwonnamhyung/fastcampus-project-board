package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면 , 게시글 리스트 반환")
    @Test
    void givenSearchParameter_whenSearchingArticles_thenReturnsArticleList() {

        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "test");

        Assertions.assertThat(articles).isNotNull();

    }

    @DisplayName("게시글을 조회하면 , 게시글 반환")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {

        ArticleDto article = sut.searchArticle(1L);

        Assertions.assertThat(article).isNotNull();

    }

    @DisplayName("게시글 정보를 입력하면 게시글 생성")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle(){

        ArticleDto dto = ArticleDto.of(LocalDateTime.now() , "KNH" , "TEST" , "TESTCONTENT" , "HASHTAG");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        sut.saveArticle(dto);

        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글의 ID와 수정 정보를 입력하면 , 게시글을 수정")
    @Test
    void givenArticleIDAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle(){

        ArticleUpdateDto dto = ArticleUpdateDto.of("TEST" , "TESTCONTENT" , "HASHTAG");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        sut.updateArticle(1L , dto);

        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글의 ID를 입력하면 , 게시글을 삭제")
    @Test
    void givenArticleID_whenDeletingArticle_thenDeleteArticle(){

        willDoNothing().given(articleRepository).delete(any(Article.class));

        sut.deleteArticle(1L);

        then(articleRepository).should().delete(any(Article.class));

    }
}