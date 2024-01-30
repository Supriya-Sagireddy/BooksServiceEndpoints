package org.example;

import org.example.controller.BookController;
import org.example.model.Book;
import org.example.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        List<Book> bookList = Arrays.asList(
                new Book( "Law of Attraction", "Supriya", "Sindhu"),
                new Book( "Purple World", "Junkook", "RM")
        );

        Mockito.when(bookService.getAllBooks()).thenReturn(bookList);

        Mockito.when(bookService.getBook(1)).thenReturn(bookList.get(0));
    }

//    @Test
//    public void getAllBooks() {
//        List<Book> allBooks = bookController.getAllBooks();
//        assertEquals(2, allBooks.size());
//    }
//
//    @Test
//    public void getBookById() {
//        Book book = bookController.getBook(1);
//        assertEquals("Law of Attraction", book.getName());
//    }
//
//    @Test
//    public void addBook() {
//        Book newBook = new Book( "Alchemist", "Sowju", "Sowjanya");
//        bookController.addBook(newBook);
//        Mockito.verify(bookService, times(1)).addBook(newBook);
//    }
//
//    @Test
//    public void updateBook() {
//        Book updatedBook = new Book( "College Life", "Suma", "Supriya");
//        bookController.updateBook(updatedBook, 1);
//        Mockito.verify(bookService, times(1)).updateBook(1, updatedBook);
//    }
//
//    @Test
//    public void deleteBook() {
//        bookController.deleteBook(1);
//        Mockito.verify(bookService, times(1)).deleteBook(1);
//    }

}

