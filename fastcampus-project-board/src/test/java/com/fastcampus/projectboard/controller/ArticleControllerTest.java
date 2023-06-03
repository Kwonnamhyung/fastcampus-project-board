package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.common.config.SecurityConfig;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.service.ArticleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class)
@Import(SecurityConfig.class)
class ArticleControllerTest {

    private final MockMvc mvc;

    @MockBean
    private ArticleService articleService;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지")
    @Test
    public void givenNoting_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {

        //Given
        given(articleService.searchArticles(eq(null) , eq(null) , any(Pageable.class))).willReturn(Page.empty());
        //When
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"));

        then(articleService).should().searchArticles(eq(null) , eq(null) , any(Pageable.class));
        //Then

    }

    @DisplayName("[view][GET] 게시글 상세 페이지")
    @Test
    public void givenNoting_whenRequestingArticleView_thenReturnsArticleView() throws Exception {

        Long articleId = 1L;

        given(articleService.searchArticle(articleId)).willReturn(createArticleResponseWithComment());
        //Given

        //When
        mvc.perform(get("/articles/" + articleId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"));
        //Then

        then(articleService).should().searchArticle(articleId);
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 검색 전용 페이지")
    @Test
    public void givenNoting_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {

        //Given

        //When
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("articles/search"))
                .andExpect(content().contentType(MediaType.TEXT_HTML));
        //Then

    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 해시태그 검색 페이지")
    @Test
    public void givenNoting_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {

        //Given

        //When
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(view().name("articles/hashtag"))
                .andExpect(content().contentType(MediaType.TEXT_HTML));
        //Then

    }

    private ArticleDto.ResponseWithComment createArticleResponseWithComment() {
        return new ArticleDto.ResponseWithComment (
                "title" ,
                "content" ,
                "hashtag",
                createUserAccountDto(),
                List.of()
        );
    }

    private UserAccountDto createUserAccountDto() {
        return new UserAccountDto(
                "knh" ,
                "1234",
                "knh@gmail.com",
                "knh",
                "memo",
               "knh",
                LocalDateTime.now(),
                "knh",
                LocalDateTime.now()
        );
    }
}