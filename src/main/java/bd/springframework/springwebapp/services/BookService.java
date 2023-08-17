package bd.springframework.springwebapp.services;

import bd.springframework.springwebapp.domain.Book;

public interface BookService {

    Iterable<Book> findAll();

}
