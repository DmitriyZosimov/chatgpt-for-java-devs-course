package com.example.service;

import com.example.model.Genre;
import com.example.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    private GenreService genreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        genreService = new GenreServiceImpl(genreRepository);
    }

    @Test
    void testGetAllGenres() {
        List<Genre> mockGenres = new ArrayList<>();
        when(genreRepository.findAll()).thenReturn(mockGenres);

        Iterable<Genre> genres = genreService.getAllGenres();

        assertEquals(mockGenres, genres);
    }

    @Test
    void testGetGenreById() {
        Long genreId = 1L;
        Genre mockGenre = new Genre();
        mockGenre.setId(genreId);

        when(genreRepository.findById(genreId)).thenReturn(Optional.of(mockGenre));

        Genre retrievedGenre = genreService.getGenreById(genreId);

        assertNotNull(retrievedGenre);
        assertEquals(genreId, retrievedGenre.getId());
    }

    @Test
    void testGetGenreByIdNotFound() {
        Long genreId = 1L;

        when(genreRepository.findById(genreId)).thenReturn(Optional.empty());

        Genre retrievedGenre = genreService.getGenreById(genreId);

        assertNull(retrievedGenre);
    }

    @Test
    void testCreateGenre() {
        Genre mockGenre = new Genre();
        mockGenre.setId(1L);

        when(genreRepository.save(any(Genre.class))).thenReturn(mockGenre);

        Genre createdGenre = genreService.createGenre(mockGenre);

        assertNotNull(createdGenre);
        assertEquals(mockGenre.getId(), createdGenre.getId());
    }

    @Test
    void testUpdateGenre() {
        Long genreId = 1L;
        Genre mockGenre = new Genre();
        mockGenre.setId(genreId);

        when(genreRepository.findById(genreId)).thenReturn(Optional.of(mockGenre));
        when(genreRepository.save(any(Genre.class))).thenReturn(mockGenre);

        Genre updatedGenre = genreService.updateGenre(genreId, mockGenre);

        assertNotNull(updatedGenre);
        assertEquals(genreId, updatedGenre.getId());
    }

    @Test
    void testUpdateGenreNotFound() {
        Long genreId = 1L;

        when(genreRepository.findById(genreId)).thenReturn(Optional.empty());

        Genre updatedGenre = genreService.updateGenre(genreId, new Genre());

        assertNull(updatedGenre);
    }

    @Test
    void testDeleteGenre() {
        Long genreId = 1L;

        genreService.deleteGenre(genreId);

        verify(genreRepository, times(1)).deleteById(genreId);
    }
}

