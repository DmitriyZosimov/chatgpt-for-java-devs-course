package com.example.controller;

import com.example.model.Genre;
import com.example.service.GenreService;
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

class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllGenres() {
        List<Genre> mockGenres = new ArrayList<>();
        when(genreService.getAllGenres()).thenReturn(mockGenres);

        ResponseEntity<Iterable<Genre>> response = genreController.getAllGenres();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockGenres, response.getBody());
    }

    @Test
    void testGetGenreById() {
        Long genreId = 1L;
        Genre mockGenre = new Genre();
        when(genreService.getGenreById(genreId)).thenReturn(mockGenre);

        ResponseEntity<Genre> response = genreController.getGenreById(genreId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockGenre, response.getBody());
    }

    @Test
    void testGetGenreByIdNotFound() {
        Long genreId = 1L;
        when(genreService.getGenreById(genreId)).thenReturn(null);

        ResponseEntity<Genre> response = genreController.getGenreById(genreId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateGenre() {
        Genre mockGenre = new Genre();
        when(genreService.createGenre(any(Genre.class))).thenReturn(mockGenre);

        ResponseEntity<Genre> response = genreController.createGenre(mockGenre);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockGenre, response.getBody());
    }

    @Test
    void testUpdateGenre() {
        Long genreId = 1L;
        Genre mockGenre = new Genre();
        when(genreService.updateGenre(eq(genreId), any(Genre.class))).thenReturn(mockGenre);

        ResponseEntity<Genre> response = genreController.updateGenre(genreId, mockGenre);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockGenre, response.getBody());
    }

    @Test
    void testUpdateGenreNotFound() {
        Long genreId = 1L;
        when(genreService.updateGenre(eq(genreId), any(Genre.class))).thenReturn(null);

        ResponseEntity<Genre> response = genreController.updateGenre(genreId, new Genre());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteGenre() {
        Long genreId = 1L;
        ResponseEntity<Void> response = genreController.deleteGenre(genreId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(genreService, times(1)).deleteGenre(genreId);
    }
}

