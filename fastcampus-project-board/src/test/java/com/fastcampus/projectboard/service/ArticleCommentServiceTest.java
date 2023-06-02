package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.UserAccount;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks
    private ArticleCommentService sut;

    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @Mock
    private ArticleRepository articleRepository;


    @DisplayName("게시글 ID로 조회하면 , 해당하는 댓글 리스트를 반환")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsComments() {

        Long articleId = 1L;

        UserAccount userAccount = UserAccount.of("KNH", "1234", "knh@gmail.com", "knh", "memo");
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of(userAccount ,"title" , "content" , "hashtag")));

        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        Assertions.assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);

    }

    @DisplayName("댓글 정보를 입력하면, 해당하는 댓글을 저장")
    @Test
    void givenArticleCommentInfo_whenSavingComments_thenSaveComments() {


        ArticleCommentDto articleCommentDto = ArticleCommentDto.of(LocalDateTime.now(), "KNH", LocalDateTime.now(), "KNH", "CONTENT");

        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        sut.saveArticleComment(articleCommentDto);

        then(articleCommentRepository).should().save(any(ArticleComment.class));

    }

    @DisplayName("게시글 댓글 ID를 입력하면, 해당하는 댓글을 삭제")
    @Test
    void givenArticleCommentInfo_whenDeletingComments_thenDeleteComments() {

        willDoNothing().given(articleCommentRepository).delete(any(ArticleComment.class));

        sut.deleteArticleComment(1L);

        then(articleCommentRepository).should().delete(any(ArticleComment.class));

    }

}