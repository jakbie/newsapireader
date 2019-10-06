package com.jakubielawski.server.model.DTO;

import com.jakubielawski.server.model.client.News;

import java.util.List;
import java.util.stream.Collectors;

public class NewsDTO {

    private String country;

    private String category;

    private List<ArticleDTO> articles;

    public NewsDTO() {
    }

    public NewsDTO(News news, String country, String category) {
        this.country = country;
        this.category = category;
        this.articles = news.getArticles().stream().map(ArticleDTO::new).collect(Collectors.toList());
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }

}
