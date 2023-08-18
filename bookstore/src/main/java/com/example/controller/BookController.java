package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        Iterable<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book bookDetails) {
        Book createdBook = bookService.createBook(bookDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        if (updatedBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<Iterable<Book>> searchBooksByTitle(@RequestParam String title) {
        Iterable<Book> books = bookService.searchBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/genre")
    public ResponseEntity<Iterable<Book>> searchBooksByGenre(@RequestParam String genre) {
        Iterable<Book> books = bookService.searchBooksByGenre(genre);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/author")
    public ResponseEntity<Iterable<Book>> searchBooksByAuthor(@RequestParam String author) {
        Iterable<Book> books = bookService.searchBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }
}

