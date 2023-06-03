package com.fastcampus.projectboard.domain;

import com.fastcampus.projectboard.common.domain.BaseEntity;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false)
    private String title;
    @Setter @Column(nullable = false , length = 10000)
    private String content;
    @Setter
    private String hashtag;

    @Setter
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private UserAccount userAccount;

    @OneToMany(mappedBy = "article" , cascade = CascadeType.ALL , fetch = FetchType.LAZY) @OrderBy("createdAt DESC ") @ToString.Exclude // tostring 연관관계 참조 끊는것
    private final List<ArticleComment> articleComment = new LinkedList<>();

    private Article(UserAccount userAccount , String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(UserAccount userAccount , String title , String content , String hashtag) {
        return new Article(userAccount ,title , content , hashtag);
    }

    public static Article toEntity(ArticleDto.Request request) {
        return Article.of(UserAccount.toEntity(request.getUserAccountDto()) , request.getTitle() , request.getContent() ,request.getHashtag());
    }

    public static ArticleDto.Request entityToRequest(Article entity) {
        return new ArticleDto.Request(
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                UserAccount.from(entity.getUserAccount()),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getModifiedBy(),
                entity.getModifiedAt()
        );
    }

    public static ArticleDto.Response entityToResponse(Article entity) {
        return new ArticleDto.Response(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                UserAccount.from(entity.getUserAccount()),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getModifiedBy(),
                entity.getModifiedAt()
        );
    }

    public static ArticleDto.ResponseWithComment entityToResponseWithComment(Article entity) {
        return new ArticleDto.ResponseWithComment(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                UserAccount.from(entity.getUserAccount()),
                getArticleCommentDtoList(entity.getArticleComment())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return getId() != null && getId().equals(article.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    private static List<ArticleCommentDto> getArticleCommentDtoList(List<ArticleComment> articleCommentList) {

        List<ArticleCommentDto> resultList = new LinkedList<>();
        for(ArticleComment articleComment : articleCommentList) {
            resultList.add(ArticleComment.from(articleComment));
        }

        return resultList;
    }
}
