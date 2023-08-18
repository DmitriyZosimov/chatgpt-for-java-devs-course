package com.example.service;

import com.example.model.Genre;
import com.example.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Iterable<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(Long genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }

    @Override
    @Transactional
    public Genre createGenre(Genre genreDetails) {
        return genreRepository.save(genreDetails);
    }

    @Override
    @Transactional
    public Genre updateGenre(Long genreId, Genre genreDetails) {
        Genre existingGenre = genreRepository.findById(genreId).orElse(null);
        if (existingGenre == null) {
            return null;
        }
        existingGenre.setBooks(genreDetails.getBooks());
        existingGenre.setName(genreDetails.getName());
        return genreRepository.save(existingGenre);
    }

    @Override
    @Transactional
    public void deleteGenre(Long genreId) {
        genreRepository.deleteById(genreId);
    }
}

