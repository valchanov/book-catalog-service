package com.example.bookcatalogclient.controller;

import com.example.bookcatalogclient.client.BookInfoClient;
import com.example.bookcatalogclient.client.BookRatingClient;
import com.example.bookcatalogclient.model.Book;
import com.example.bookcatalogclient.model.CatalogItem;
import com.example.bookcatalogclient.model.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class BookCatalogCtrl {
    private final BookInfoClient bookInfoClient;
    private final BookRatingClient bookRatingClient;

    @GetMapping("/list")
    public List<CatalogItem> getCatalog() {
        Flux<Book> booksInfo = bookInfoClient.getAllBooks();
        Flux<Rating> booksRating = bookRatingClient.getAllBooks();

        List<CatalogItem> catalog = new ArrayList<>();

        booksInfo.toStream()
                 .forEach(b -> {
                     int currentRating = booksRating.toStream()
                                                    .filter(r -> r.getBookid()
                                                                  .equals(b.getId()))
                                                    .findFirst()
                                                    .map(Rating::getRating)
                                                    .orElse(0);

                     catalog.add(new CatalogItem(b.getTitle(), b.getAuthor(), b.getDescription(), currentRating));
                 });

        return catalog;
    }

    @GetMapping("/{bookId}")
    public Mono<CatalogItem> getCatalogItem(@PathVariable("bookId") Integer bookId) {

        Mono<Book> book = bookInfoClient.findById(bookId);
        Mono<Rating> rating = bookRatingClient.findById(bookId);
        return Mono.zip(book, rating)
                   .map(tuple -> {
                       var bookResult = tuple.getT1();
                       var ratingResult = tuple.getT2();
                       return new CatalogItem(bookResult.getTitle(),
                                              bookResult.getAuthor(),
                                              bookResult.getDescription(),
                                              ratingResult.getRating());
                   });
    }
}
