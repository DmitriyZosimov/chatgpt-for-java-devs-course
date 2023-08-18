package com.example.controller;

import com.example.model.Author;
import com.example.service.AuthorService;
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
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllAuthors() {
        List<Author> mockAuthors = new ArrayList<>();
        when(authorService.getAllAuthors()).thenReturn(mockAuthors);

        ResponseEntity<Iterable<Author>> response = authorController.getAllAuthors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAuthors, response.getBody());
    }

    @Test
    void testGetAuthorById() {
        Long authorId = 1L;
        Author mockAuthor = new Author();
        when(authorService.getAuthorById(authorId)).thenReturn(mockAuthor);

        ResponseEntity<Author> response = authorController.getAuthorById(authorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAuthor, response.getBody());
    }

    @Test
    void testGetAuthorByIdNotFound() {
        Long authorId = 1L;
        when(authorService.getAuthorById(authorId)).thenReturn(null);

        ResponseEntity<Author> response = authorController.getAuthorById(authorId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateAuthor() {
        Author mockAuthor = new Author();
        when(authorService.createAuthor(any(Author.class))).thenReturn(mockAuthor);

        ResponseEntity<Author> response = authorController.createAuthor(mockAuthor);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockAuthor, response.getBody());
    }

    @Test
    void testUpdateAuthor() {
        Long authorId = 1L;
        Author mockAuthor = new Author();
        when(authorService.updateAuthor(eq(authorId), any(Author.class))).thenReturn(mockAuthor);

        ResponseEntity<Author> response = authorController.updateAuthor(authorId, mockAuthor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAuthor, response.getBody());
    }

    @Test
    void testUpdateAuthorNotFound() {
        Long authorId = 1L;
        when(authorService.updateAuthor(eq(authorId), any(Author.class))).thenReturn(null);

        ResponseEntity<Author> response = authorController.updateAuthor(authorId, new Author());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteAuthor() {
        Long authorId = 1L;
        ResponseEntity<Void> response = authorController.deleteAuthor(authorId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(authorService, times(1)).deleteAuthor(authorId);
    }
}

