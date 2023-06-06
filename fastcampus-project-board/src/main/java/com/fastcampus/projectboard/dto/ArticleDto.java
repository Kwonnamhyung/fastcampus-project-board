package com.fastcampus.projectboard.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A DTO for the {@link com.fastcampus.projectboard.domain.Article} entity
 */
public class ArticleDto {

    @Data
    public static class Request {
        private final String title;
        private final String content;
        private final String hashtag;
        private final UserAccountDto userAccountDto;
        private final String createdBy;
        private final LocalDateTime createdAt;
        private final String modifiedBy;
        private final LocalDateTime modifiedAt;

        public Request(String title, String content, String hashtag, UserAccountDto userAccountDto, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt) {
            this.title = title;
            this.content = content;
            this.hashtag = hashtag;
            this.userAccountDto = userAccountDto;
            this.createdBy = createdBy;
            this.createdAt = createdAt;
            this.modifiedBy = modifiedBy;
            this.modifiedAt = modifiedAt;
        }
    }

    @Data
    public static class Response {

        private Long articleId;
        private final String title;
        private final String content;
        private final String hashtag;
        private final UserAccountDto userAccountDto;
        private final String createdBy;
        private final LocalDateTime createdAt;
        private final String modifiedBy;
        private final LocalDateTime modifiedAt;

        public Response(Long articleId ,String title, String content, String hashtag, UserAccountDto userAccountDto, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt) {
            this.articleId = articleId;
            this.title = title;
            this.content = content;
            this.hashtag = hashtag;
            this.userAccountDto = userAccountDto;
            this.createdBy = createdBy;
            this.createdAt = createdAt;
            this.modifiedBy = modifiedBy;
            this.modifiedAt = modifiedAt;
        }
    }

    @Data
    public static class ResponseWithComment {


        private final Long articleId;
        private final String title;
        private final String content;
        private final String hashtag;
        private final UserAccountDto userAccountDto;
        private final List<ArticleCommentDto> articleCommentList;

        private final String createdBy;
        private final LocalDateTime createdAt;
        private final String modifiedBy;
        private final LocalDateTime modifiedAt;


        public ResponseWithComment(Long articleId, String title, String content, String hashtag, UserAccountDto userAccountDto, List<ArticleCommentDto> articleCommentList, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt) {
            this.articleId = articleId;
            this.title = title;
            this.content = content;
            this.hashtag = hashtag;
            this.userAccountDto = userAccountDto;
            this.articleCommentList = articleCommentList;
            this.createdBy = createdBy;
            this.createdAt = createdAt;
            this.modifiedBy = modifiedBy;
            this.modifiedAt = modifiedAt;
        }
    }
}