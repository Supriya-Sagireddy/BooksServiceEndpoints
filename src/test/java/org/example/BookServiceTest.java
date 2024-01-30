package org.example;

import org.example.model.Book;
import org.example.repo.BookRepo;
import org.example.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        List<Book> bookList = Arrays.asList(
                new Book("Law of Attraction", "Supriya", "Sindhu"),
                new Book("Purple World", "Junkook", "RM")
        );

        Mockito.when(bookRepo.findAll()).thenReturn(bookList);

        Mockito.when(bookRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(bookList.get(0)));
        Mockito.when(bookRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
    }

    @Test
    public void getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        assertEquals(2, allBooks.size());
    }

    @Test
    public void getBookById() {
        Book book = bookService.getBook(1);
        System.out.println(book);
        assertEquals("Law of Attraction", book.getName());
    }
    @Test
    public void addBook() {
        Book newBook = new Book( "Alchemist", "Sowju", "Sowjanya");
        bookService.addBook(newBook);
        Mockito.verify(bookRepo, Mockito.times(1)).save(newBook);
    }

    @Test
    public void updateBook() {
        Book updatedBook = new Book( "College Life", "Suma", "Supriya");
        bookService.updateBook(1, updatedBook);
        Mockito.verify(bookRepo, Mockito.times(1)).save(updatedBook);
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(1);
        Mockito.verify(bookRepo, Mockito.times(1)).deleteById(1);
    }
}
