package com.jakubielawski.server.model.DTO;

import com.jakubielawski.server.model.client.Article;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class ArticleDTO {

    private String author;

    private String title;

    private String description;

    private String date;

    private String sourceName;

    private String articleUrl;

    private String imageUrl;

    public ArticleDTO() {
    }

    public ArticleDTO(String author, String title, String description, String date, String sourceName, String articleUrl, String imageUrl) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.date = date;
        this.sourceName = sourceName;
        this.articleUrl = articleUrl;
        this.imageUrl = imageUrl;
    }

    public ArticleDTO(Article article) {
        this.author = article.getAuthor();
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.date = this.formatDate(article.getPublishedAt());
        this.sourceName = article.getSource().getName();
        this.articleUrl = article.getUrl();
        this.imageUrl = article.getUrlToImage();
    }

    private String formatDate(String date) {

        LocalDate localDate = LocalDateTime
                .ofInstant(Instant.parse(date), ZoneOffset.UTC)
                .toLocalDate();

        return localDate.format(DateTimeFormatter.ofPattern("yyyy MM dd")).toString();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
