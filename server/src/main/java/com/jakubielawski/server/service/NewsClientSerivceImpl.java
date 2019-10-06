package com.jakubielawski.server.service;

import com.jakubielawski.server.model.client.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service()
public class NewsClientSerivceImpl implements NewsClientService {

    private WebClient webClient;
    private Environment env;

    @Autowired
    public void NewsClientServiceImpl(Environment env) {
        this.env = env;
        this.webClient = WebClient.builder()
                .baseUrl(env.getProperty("api.endpoint"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .build();
    }


    @Override
    public Mono<News> getNews(Optional<String> country, Optional<String> category) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("apiKey", env.getProperty("api.key"));
        if(country.isPresent()) {
            params.add("country", country.get());
        }
        if(category.isPresent()) {
            params.add("category", category.get());
        }

        Mono<News> result = webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParams(params).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(News.class);

        return result;
    }

}
