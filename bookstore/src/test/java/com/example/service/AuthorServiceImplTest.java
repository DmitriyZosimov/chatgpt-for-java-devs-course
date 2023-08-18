package com.example.service;

import com.example.model.Author;
import com.example.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authorService = new AuthorServiceImpl(authorRepository);
    }

    @Test
    void testGetAllAuthors() {
        List<Author> mockAuthors = new ArrayList<>();
        mockAuthors.add(new Author());
        when(authorRepository.findAll()).thenReturn(mockAuthors);

        Iterable<Author> authors = authorService.getAllAuthors();

        assertEquals(mockAuthors, authors);
    }

    @Test
    void testGetAuthorById() {
        Long authorId = 1L;
        Author mockAuthor = new Author();
        mockAuthor.setId(authorId);

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(mockAuthor));

        Author retrievedAuthor = authorService.getAuthorById(authorId);

        assertNotNull(retrievedAuthor);
        assertEquals(authorId, retrievedAuthor.getId());
    }

    @Test
    void testGetAuthorByIdNotFound() {
        Long authorId = 1L;

        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        Author retrievedAuthor = authorService.getAuthorById(authorId);

        assertNull(retrievedAuthor);
    }

    @Test
    void testCreateAuthor() {
        Author mockAuthor = new Author();
        mockAuthor.setId(1L);

        when(authorRepository.save(any(Author.class))).thenReturn(mockAuthor);

        Author createdAuthor = authorService.createAuthor(mockAuthor);

        assertNotNull(createdAuthor);
        assertEquals(mockAuthor.getId(), createdAuthor.getId());
    }

    @Test
    void testUpdateAuthor() {
        Long authorId = 1L;
        Author mockAuthor = new Author();
        mockAuthor.setId(authorId);

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(mockAuthor));
        when(authorRepository.save(any(Author.class))).thenReturn(mockAuthor);

        Author updatedAuthor = authorService.updateAuthor(authorId, mockAuthor);

        assertNotNull(updatedAuthor);
        assertEquals(authorId, updatedAuthor.getId());
    }

    @Test
    void testUpdateAuthorNotFound() {
        Long authorId = 1L;

        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        Author updatedAuthor = authorService.updateAuthor(authorId, new Author());

        assertNull(updatedAuthor);
    }

    @Test
    void testDeleteAuthor() {
        Long authorId = 1L;

        authorService.deleteAuthor(authorId);

        verify(authorRepository, times(1)).deleteById(authorId);
    }
}

