package com.jakubielawski.server.rest;

import com.jakubielawski.server.model.DTO.NewsDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = webTestClient
                .mutate()
                .responseTimeout(Duration.ofMillis(25000))
                .build();
    }

    @Test
    public void testGetNews1() {
        webTestClient.get()
                .uri("news")
                .exchange()
                .expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void testGetNews2() {
        webTestClient.get()
                .uri("news/{country}", "pl")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(NewsDTO.class)
                .consumeWith(newsDTO -> {
                    assertEquals("pl",newsDTO.getResponseBody().getCountry());
                    assertEquals("",newsDTO.getResponseBody().getCategory());
                    assertTrue(newsDTO.getResponseBody().getArticles().size() > 1);
                });
    }

    @Test
    public void testGetNews3() {
        webTestClient.get()
                .uri("news/{country}/{category}", "pl","technology")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(NewsDTO.class)
                .consumeWith(newsDTO -> {
                    assertEquals("pl",newsDTO.getResponseBody().getCountry());
                    assertEquals("technology",newsDTO.getResponseBody().getCategory());
                    assertTrue(newsDTO.getResponseBody().getArticles().size() > 1);
                });
    }

}
