package bd.springframework.springwebapp.services;

import bd.springframework.springwebapp.domain.Author;

public interface AuthorService {
    Iterable<Author> findAll();
}
