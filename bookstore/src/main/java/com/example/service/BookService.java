package com.example.service;

import com.example.model.Book;

public interface BookService {
    /**
     * Retrieve a Iterable of all books.
     *
     * @return Iterable of all books.
     */
    Iterable<Book> getAllBooks();

    /**
     * Retrieve books containing the specified title.
     *
     * @param title Title to search for.
     * @return Iterable of books containing the title.
     */
    Iterable<Book> searchBooksByTitle(String title);

    /**
     * Retrieve books of the specified genre.
     *
     * @param genreName Genre name to search for.
     * @return Iterable of books of the specified genre.
     */
    Iterable<Book> searchBooksByGenre(String genreName);

    /**
     * Retrieve books by the specified author.
     *
     * @param authorName Author name to search for.
     * @return Iterable of books by the specified author.
     */
    Iterable<Book> searchBooksByAuthor(String authorName);

    /**
     * Retrieve a book by its ID.
     *
     * @param bookId ID of the book.
     * @return Book with the specified ID, or null if not found.
     */
    Book getBookById(Long bookId);

    /**
     * Create a new book.
     *
     * @param bookDetails Book details to create.
     * @return Created book.
     */
    Book createBook(Book bookDetails);

    /**
     * Update book details.
     *
     * @param bookId      ID of the book to update.
     * @param bookDetails Updated book details.
     * @return Updated book.
     */
    Book updateBook(Long bookId, Book bookDetails);

    /**
     * Delete a book by its ID.
     *
     * @param bookId ID of the book to delete.
     */
    void deleteBook(Long bookId);
}

