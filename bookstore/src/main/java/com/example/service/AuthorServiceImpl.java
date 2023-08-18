package com.example.service;

import com.example.model.Author;
import com.example.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    @Transactional
    public Author createAuthor(Author authorDetails) {
        return authorRepository.save(authorDetails);
    }

    @Override
    @Transactional
    public Author updateAuthor(Long authorId, Author authorDetails) {
        Author existingAuthor = authorRepository.findById(authorId).orElse(null);
        if (existingAuthor == null) {
            return null;
        }
        existingAuthor.setBooks(authorDetails.getBooks());
        existingAuthor.setName(authorDetails.getName());
        return authorRepository.save(existingAuthor);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}

