package org.example.controller;

import org.example.jwt.JwtUtil;
import org.example.model.AuthenticationRequest;
import org.example.model.AuthenticationResponse;
import org.example.service.BookService;
import org.example.model.Book;
import org.example.springSecurityWeb.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
     private JwtUtil jwtTokenUtil;
    @Autowired
    private BookService bookService;
    private static final Logger LOGGER= LoggerFactory.getLogger(BookController.class);

    @RequestMapping(method =RequestMethod.POST,value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
        try {
            LOGGER.info("Authenticating User Details");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            LOGGER.error("Incorrect userName or Password");
            throw new Exception("Incorrect userName or Password",e);
        }
        final UserDetails userDetails=userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt=jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(method=RequestMethod.GET,value="/books")
    public List<Book> getAllBooks(){
        LOGGER.info("Calling Book Sevice");
        return bookService.getAllBooks();

    }
    @RequestMapping(method = RequestMethod.GET,value="/books/{id}")
    public Book getBook(@PathVariable int id){
        LOGGER.info("Calling books by Id");
        return bookService.getBook(id);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public String addBook(@RequestBody Book book){
        LOGGER.info("Calling BookService to add book to BookService");
        bookService.addBook(book);
        return "Book added succeesfully";
    }

    @RequestMapping(method = RequestMethod.PUT,value="/books/{id}")
    public String updateBook(@RequestBody Book book, @PathVariable int id){
        LOGGER.info("Calling bookService to Update the book by id");
        bookService.updateBook(id, book);

        return "Book Updated Succesfully";
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/books/{id}")
    public String deleteBook(@PathVariable int id){
        LOGGER.info("Calling bookser2vice to delete the book by id");
        bookService.deleteBook(id);
        return "Book deleted succesfully";
    }

}
