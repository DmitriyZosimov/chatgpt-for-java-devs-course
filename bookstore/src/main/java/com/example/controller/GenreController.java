package com.example.controller;

import com.example.model.Genre;
import com.example.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Genre>> getAllGenres() {
        Iterable<Genre> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        Genre genre = genreService.getGenreById(id);
        if (genre == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genre);
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genreDetails) {
        Genre createdGenre = genreService.createGenre(genreDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genreDetails) {
        Genre updatedGenre = genreService.updateGenre(id, genreDetails);
        if (updatedGenre == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}

