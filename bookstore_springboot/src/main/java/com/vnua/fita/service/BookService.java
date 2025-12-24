package com.vnua.fita.service;

import com.vnua.fita.entity.Book;
import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
}