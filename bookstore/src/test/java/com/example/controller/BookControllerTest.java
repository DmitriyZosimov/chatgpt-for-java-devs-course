package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllBooks() {
        List<Book> mockBooks = new ArrayList<>();
        when(bookService.getAllBooks()).thenReturn(mockBooks);

        ResponseEntity<Iterable<Book>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBooks, response.getBody());
    }

    @Test
    void testGetBookById() {
        Long bookId = 1L;
        Book mockBook = new Book();
        when(bookService.getBookById(bookId)).thenReturn(mockBook);

        ResponseEntity<Book> response = bookController.getBookById(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBook, response.getBody());
    }

    @Test
    void testGetBookByIdNotFound() {
        Long bookId = 1L;
        when(bookService.getBookById(bookId)).thenReturn(null);

        ResponseEntity<Book> response = bookController.getBookById(bookId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateBook() {
        Book mockBook = new Book();
        when(bookService.createBook(any(Book.class))).thenReturn(mockBook);

        ResponseEntity<Book> response = bookController.createBook(mockBook);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockBook, response.getBody());
    }

    @Test
    void testUpdateBook() {
        Long bookId = 1L;
        Book mockBook = new Book();
        when(bookService.updateBook(eq(bookId), any(Book.class))).thenReturn(mockBook);

        ResponseEntity<Book> response = bookController.updateBook(bookId, mockBook);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBook, response.getBody());
    }

    @Test
    void testUpdateBookNotFound() {
        Long bookId = 1L;
        when(bookService.updateBook(eq(bookId), any(Book.class))).thenReturn(null);

        ResponseEntity<Book> response = bookController.updateBook(bookId, new Book());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;
        ResponseEntity<Void> response = bookController.deleteBook(bookId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService, times(1)).deleteBook(bookId);
    }

    @Test
    void testSearchBooksByTitle() {
        List<Book> mockBooks = new ArrayList<>();
        when(bookService.searchBooksByTitle(anyString())).thenReturn(mockBooks);

        ResponseEntity<Iterable<Book>> response = bookController.searchBooksByTitle("Sample Title");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBooks, response.getBody());
    }

    @Test
    void testSearchBooksByGenre() {
        List<Book> mockBooks = new ArrayList<>();
        when(bookService.searchBooksByGenre(anyString())).thenReturn(mockBooks);

        ResponseEntity<Iterable<Book>> response = bookController.searchBooksByGenre("Fiction");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBooks, response.getBody());
    }

    @Test
    void testSearchBooksByAuthor() {
        List<Book> mockBooks = new ArrayList<>();
        when(bookService.searchBooksByAuthor(anyString())).thenReturn(mockBooks);

        ResponseEntity<Iterable<Book>> response = bookController.searchBooksByAuthor("John Doe");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBooks, response.getBody());
    }
}

