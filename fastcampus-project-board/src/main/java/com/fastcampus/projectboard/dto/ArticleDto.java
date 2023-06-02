package com.fastcampus.projectboard.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fastcampus.projectboard.domain.Article} entity
 */
@Data
public class ArticleDto{
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final String title;
    private final String content;
    private final String hashtag;

    public static ArticleDto of(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {

        return new ArticleDto(createdAt, createdBy, title, content, hashtag);

    }
}