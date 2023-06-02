package com.fastcampus.projectboard.domain;

import com.fastcampus.projectboard.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
    private final Set<ArticleComment> articleComment = new LinkedHashSet<>();

    private Article(UserAccount userAccount , String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(UserAccount userAccount , String title , String content , String hashtag) {
        return new Article(userAccount ,title , content , hashtag);
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
}
