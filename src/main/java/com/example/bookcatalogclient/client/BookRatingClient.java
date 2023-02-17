package com.example.bookcatalogclient.client;

import com.example.bookcatalogclient.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@HttpExchange(url = "/ratings", accept = "application/json", contentType = "application/json")
public interface BookRatingClient {

    @GetExchange
    public Flux<Rating> getAllBooks();

    @GetExchange("/{bookId}")
    public Mono<Rating> findById(@PathVariable int bookId);

}
