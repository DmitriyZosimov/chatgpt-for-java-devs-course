package com.example.service;

import com.example.model.Genre;

public interface GenreService {
    /**
     * Retrieve a Iterable of all genres.
     *
     * @return Iterable of all genres.
     */
    Iterable<Genre> getAllGenres();

    /**
     * Retrieve a genre by its ID.
     *
     * @param genreId ID of the genre.
     * @return Genre with the specified ID, or null if not found.
     */
    Genre getGenreById(Long genreId);

    /**
     * Create a new genre.
     *
     * @param genreDetails Genre details to create.
     * @return Created genre.
     */
    Genre createGenre(Genre genreDetails);

    /**
     * Update genre details.
     *
     * @param genreId      ID of the genre to update.
     * @param genreDetails Updated genre details.
     * @return Updated genre.
     */
    Genre updateGenre(Long genreId, Genre genreDetails);

    /**
     * Delete a genre by its ID.
     *
     * @param genreId ID of the genre to delete.
     */
    void deleteGenre(Long genreId);
}

