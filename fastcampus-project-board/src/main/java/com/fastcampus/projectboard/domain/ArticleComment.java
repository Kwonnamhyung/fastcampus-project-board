package com.fastcampus.projectboard.domain;

import com.fastcampus.projectboard.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleComment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false)
    private String content;
    @Setter @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private Article article;

    @Setter
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    private UserAccount userAccount;

    private ArticleComment(UserAccount userAccount , String content, Article article) {
        this.userAccount = userAccount;
        this.content = content;
        this.article = article;
    }

    public static ArticleComment of(UserAccount userAccount , Article article , String content) {
        return new ArticleComment(userAccount ,content , article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
