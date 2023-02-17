package com.example.bookcatalogclient.config;

import com.example.bookcatalogclient.client.BookInfoClient;
import com.example.bookcatalogclient.client.BookRatingClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ApiConfig {

    @Bean
    BookInfoClient bookInfoClient(@Value("${book.info.url}") String bookInfoUrl) {
        var webClient = WebClient.builder()
                                 .baseUrl(bookInfoUrl)
                                 .build();

        var factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                                             .build();

        return factory.createClient(BookInfoClient.class);
    }

    @Bean
    BookRatingClient bookratingClient(@Value("${book.rating.url}") String bookRatingUrl) {
        var webClient = WebClient.builder()
                                 .baseUrl(bookRatingUrl)
                                 .build();

        var factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                                             .build();

        return factory.createClient(BookRatingClient.class);
    }
}
