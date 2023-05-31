package com.fastcampus.projectboard.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fastcampus.projectboard.domain.ArticleComment} entity
 */
@Data
public class ArticleCommentDto {
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;
    private final String content;


    public static ArticleCommentDto of(LocalDateTime createdAt , String createdBy , LocalDateTime modifiedAt , String modifiedBy , String content) {
        return new ArticleCommentDto(createdAt, createdBy, modifiedAt, modifiedBy, content);
    }
}