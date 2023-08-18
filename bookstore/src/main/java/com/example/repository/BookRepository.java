package com.example.repository;

import com.example.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    // Add custom query methods if needed
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByGenreNameIgnoreCase(String genreName);
    List<Book> findByAuthorNameIgnoreCase(String authorName);
}

