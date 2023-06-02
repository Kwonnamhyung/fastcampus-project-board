package com.fastcampus.projectboard.dto;

import lombok.Data;

/**
 * A DTO for the {@link com.fastcampus.projectboard.domain.Article} entity
 */
@Data
public class ArticleUpdateDto{

    private final String title;
    private final String content;
    private final String hashtag;

    public static ArticleUpdateDto of(String title, String content, String hashtag) {

        return new ArticleUpdateDto(title, content, hashtag);

    }
}