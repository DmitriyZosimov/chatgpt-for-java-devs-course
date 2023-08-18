package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Iterable<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Iterable<Book> searchBooksByGenre(String genreName) {
        return bookRepository.findByGenreNameIgnoreCase(genreName);
    }

    @Override
    public Iterable<Book> searchBooksByAuthor(String authorName) {
        return bookRepository.findByAuthorNameIgnoreCase(authorName);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    @Transactional
    public Book createBook(Book bookDetails) {
        return bookRepository.save(bookDetails);
    }

    @Override
    @Transactional
    public Book updateBook(Long bookId, Book bookDetails) {
        Book existingBook = bookRepository.findById(bookId).orElse(null);
        if (existingBook == null) {
            return null;
        }
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setPrice(bookDetails.getPrice());
        existingBook.setGenre(bookDetails.getGenre());
        existingBook.setQuantityAvailable(bookDetails.getQuantityAvailable());
        return bookRepository.save(existingBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}

