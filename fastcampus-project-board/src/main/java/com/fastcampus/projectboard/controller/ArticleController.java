package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    public String articles(
            @RequestParam(required = false) SearchType searchType ,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10 , sort = "createdAt" , direction = Sort.Direction.DESC) Pageable pageable ,
            ModelMap map
    ) {

        map.addAttribute("articles" , articleService.searchArticles(searchType , searchValue , pageable));
        return "articles/index";

    }

    @GetMapping("{articleId}")
    public String article(@PathVariable Long articleId , ModelMap map) {

        ArticleDto.ResponseWithComment article = articleService.searchArticle(articleId);
        map.addAttribute("article" , article);
        map.addAttribute("articleComments" , article.getArticleCommentList());

        return "articles/detail";
    }

}
