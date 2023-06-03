package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.UserAccount;
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
import org.springframework.data.domain.Pageable;

import java.util.Optional;

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
        Pageable pageable = Pageable.ofSize(20);
        String title = "title";
        SearchType type = SearchType.TITLE;
        given(articleRepository.findByTitleContaining(title , pageable)).willReturn(Page.empty());
        Page<ArticleDto.Response> articles = sut.searchArticles(SearchType.TITLE, "title" , pageable);

        Assertions.assertThat(articles).isNotNull();

    }

    @DisplayName("키워드 없이 검색할 경우 , 게시글 전체 리스트 반환")
    @Test
    void givenNothingKeyword_whenSearchingArticles_thenReturnsAllArticleList() {


        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        sut.searchArticles(SearchType.TITLE ,null,pageable);

        then(articleRepository).should().findAll(pageable);
    }

    @DisplayName("게시글을 조회하면 , 게시글 반환")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {

        Long articleId = 1L;
        UserAccount userAccount = UserAccount.of("knh", "1234", "knh@gmail.com", "knh", "memo");
        Article article = Article.of(userAccount, "title", "content", "hashtag");
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        ArticleDto.ResponseWithComment responseWithComment = sut.searchArticle(articleId);

        Assertions.assertThat(responseWithComment).isNotNull();
        Assertions.assertThat(responseWithComment.getTitle()).isEqualTo("title");

    }

    @DisplayName("게시글 정보를 입력하면 게시글 생성")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle(){

        Long articleId = 1L;
        UserAccount userAccount = UserAccount.of("knh", "1234", "knh@gmail.com", "knh", "memo");
        Article article = Article.of(userAccount, "title", "content", "hashtag");
        ArticleDto.Request request = Article.entityToRequest(article);
        given(articleRepository.save(any(Article.class))).willReturn(null);

        sut.saveArticle(request);

        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글의 ID와 수정 정보를 입력하면 , 게시글을 수정")
    @Test
    void givenArticleIDAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle(){

        Long articleId = 1L;
        UserAccount userAccount = UserAccount.of("knh", "1234", "knh@gmail.com", "knh", "memo");
        Article article = Article.of(userAccount, "title", "content", "hashtag");
        ArticleUpdateDto dto = ArticleUpdateDto.of("TEST" , "TESTCONTENT" , "HASHTAG");

        given(articleRepository.getReferenceById(articleId)).willReturn(article);
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        sut.updateArticle(articleId , dto);

        ArticleDto.ResponseWithComment response = sut.searchArticle(articleId);

        Assertions.assertThat(response.getTitle()).isEqualTo("TEST");

    }

    @DisplayName("게시글의 ID를 입력하면 , 게시글을 삭제")
    @Test
    void givenArticleID_whenDeletingArticle_thenDeleteArticle(){

        Long articleId = 1L;
        willDoNothing().given(articleRepository).deleteById(articleId);

        sut.deleteArticle(articleId);

        then(articleRepository).should().deleteById(articleId);

    }
}