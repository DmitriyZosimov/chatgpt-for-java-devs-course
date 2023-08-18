package com.example.service;

import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void testGetAllBooks() throws JsonProcessingException {
        List<Book> mockBooks = new ArrayList<>();
        Author author = Author.builder().name("Author name").build();
        Book book = Book.builder().title("TITLE").build();
        author.addBook(book);
        mockBooks.add(book);
        when(bookRepository.findAll()).thenReturn(mockBooks);

        Iterable<Book> books = bookService.getAllBooks();
        System.out.println(new ObjectMapper().writeValueAsString(books));

        assertEquals(mockBooks, books);
    }

    @Test
    void testGetBookById() {
        Long bookId = 1L;
        Book mockBook = new Book();
        mockBook.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

        Book retrievedBook = bookService.getBookById(bookId);

        assertNotNull(retrievedBook);
        assertEquals(bookId, retrievedBook.getId());
    }

    @Test
    void testGetBookByIdNotFound() {
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Book retrievedBook = bookService.getBookById(bookId);

        assertNull(retrievedBook);
    }

    @Test
    void testCreateBook() {
        Book mockBook = new Book();
        mockBook.setId(1L);

        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        Book createdBook = bookService.createBook(mockBook);

        assertNotNull(createdBook);
        assertEquals(mockBook.getId(), createdBook.getId());
    }

    @Test
    void testUpdateBook() {
        Long bookId = 1L;
        Book mockBook = new Book();
        mockBook.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));
        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        Book updatedBook = bookService.updateBook(bookId, mockBook);

        assertNotNull(updatedBook);
        assertEquals(bookId, updatedBook.getId());
    }

    @Test
    void testUpdateBookNotFound() {
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Book updatedBook = bookService.updateBook(bookId, new Book());

        assertNull(updatedBook);
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    void testSearchBooksByTitle() {
        String title = "Sample Book";
        List<Book> mockBooks = new ArrayList<>();
        when(bookRepository.findByTitleContainingIgnoreCase(title)).thenReturn(mockBooks);

        Iterable<Book> books = bookService.searchBooksByTitle(title);

        assertEquals(mockBooks, books);
    }

    @Test
    void testSearchBooksByGenre() {
        String genre = "Fiction";
        List<Book> mockBooks = new ArrayList<>();
        when(bookRepository.findByGenreNameIgnoreCase(genre)).thenReturn(mockBooks);

        Iterable<Book> books = bookService.searchBooksByGenre(genre);

        assertEquals(mockBooks, books);
    }

    @Test
    void testSearchBooksByAuthor() {
        String author = "John Doe";
        List<Book> mockBooks = new ArrayList<>();
        when(bookRepository.findByAuthorNameIgnoreCase(author)).thenReturn(mockBooks);

        Iterable<Book> books = bookService.searchBooksByAuthor(author);

        assertEquals(mockBooks, books);
    }
}

