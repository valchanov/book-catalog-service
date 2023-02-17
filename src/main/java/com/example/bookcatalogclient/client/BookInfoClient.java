package com.example.bookcatalogclient.client;

import com.example.bookcatalogclient.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@HttpExchange(url = "/books", accept = "application/json", contentType = "application/json")
public interface BookInfoClient {

    @GetExchange
    public Flux<Book> getAllBooks();

    @GetMapping(value = "/{bookId}")
    public Mono<Book> findById(@PathVariable int bookId);
}
