package com.jakubielawski.server.controller;

import com.jakubielawski.server.model.DTO.NewsDTO;
import com.jakubielawski.server.service.NewsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
public class NewsController {

    private NewsClientService newsClientService;

    public NewsController(NewsClientService newsClientService) {
        this.newsClientService = newsClientService;
    }

    @GetMapping(value = {"news","news/{country}", "news/{country}/{category}"})
    public Mono<NewsDTO> getNews(@PathVariable Optional<String> country, @PathVariable Optional<String> category) {

        if(country.isPresent()) {
            return newsClientService.getNews(country,category)
                    .map(news -> new NewsDTO(news,country.get(), category.isPresent() ? category.get() : ""));
        }

        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad URI. Follow pattern: 'news/{country}/{category}' (required at least country arg)"));
    }

}
