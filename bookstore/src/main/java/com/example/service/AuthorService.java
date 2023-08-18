package com.example.service;

import com.example.model.Author;

public interface AuthorService {
    /**
     * Retrieve a Iterable of all authors.
     *
     * @return Iterable of all authors.
     */
    Iterable<Author> getAllAuthors();

    /**
     * Retrieve an author by their ID.
     *
     * @param authorId ID of the author.
     * @return Author with the specified ID, or null if not found.
     */
    Author getAuthorById(Long authorId);

    /**
     * Create a new author.
     *
     * @param authorDetails Author details to create.
     * @return Created author.
     */
    Author createAuthor(Author authorDetails);

    /**
     * Update author details.
     *
     * @param authorId      ID of the author to update.
     * @param authorDetails Updated author details.
     * @return Updated author.
     */
    Author updateAuthor(Long authorId, Author authorDetails);

    /**
     * Delete an author by their ID.
     *
     * @param authorId ID of the author to delete.
     */
    void deleteAuthor(Long authorId);
}

