package org.example.service;

import org.example.model.Book;
import org.example.repo.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    private static final Logger LOGGER= LoggerFactory.getLogger(BookService.class);
    @Autowired
    BookRepo bookRepo;
    public List <Book>getAllBooks(){
        LOGGER.info("Calling bookrepo to get all books");
        return bookRepo.findAll();
    }

    public Book getBook(int id){
        LOGGER.info("Calling books by bookId");
        return bookRepo.findById(id).get();
    }

    public void addBook(Book book) {
        LOGGER.info("calling bookrepo to save book");
        bookRepo.save(book);
    }

    public void updateBook(int id, Book book) {
        LOGGER.info("Calling book by id to update the book");
        Book b=bookRepo.findById(id).get();
        b.setName(book.getName());
        b.setAuthor(book.getAuthor());
        b.setPublisher(book.getPublisher());
        LOGGER.info("calling bookrepo to save the book");
        bookRepo.save(b);

    }

    public void deleteBook(int id) {
        LOGGER.info("Calling book by id to delete the book");
        bookRepo.deleteById(id);
    }
}
