package org.example;

import org.example.model.Book;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
public class BookControllerExtendTest extends AbstractTest {

    @Override
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getBooksList() throws Exception {
        String uri = "/books";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Book[] bookslist = super.mapFromJson(content, Book[].class);
        assertTrue(bookslist.length > 0);
    }
    @Test
    public void addBook() throws Exception{
        String uri="/books";
        Book books = new Book();
        books.setId(1);
        books.setName("ramkarishna");
        books.setPublisher("ramakrishna");
        books.setAuthor("ramky");
        String inputJson = super.mapToJson(books);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200,status);
        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(content,"Book added succeesfully");
    }
    @Test
    public void getBookById() throws Exception{
        String uri="/books/2";
        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200,status);
        String content=mvcResult.getResponse().getContentAsString();
        assertTrue(content.length()>0);
    }
    @Test
    public void updateBook() throws Exception{
        String uri="/books/2";
        Book book=new Book();
        book.setPublisher("Suppu");
        book.setAuthor("priya");
        book.setName("Riya");
        String inputJson=super.mapToJson(book);
        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.put(uri).contentType((MediaType.APPLICATION_JSON_VALUE)).content(inputJson)).andReturn();
        int status=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200,status);
        String content=mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(content,"Book Updated Succesfully");
    }
    @Test
    public void delBook() throws Exception{
        String uri="/books/2";
        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200,status);
        String content=mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(content,"Book deleted succesfully");
    }
}
