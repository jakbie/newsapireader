package com.jakubielawski.server.service;

import com.jakubielawski.server.model.client.News;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface NewsClientService {
    Mono<News> getNews(Optional<String> country, Optional<String> category);
}
