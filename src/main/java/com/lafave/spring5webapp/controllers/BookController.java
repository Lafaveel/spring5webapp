package com.lafave.spring5webapp.controllers;

import com.lafave.spring5webapp.model.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model) {

        //JPA gets all books from db and it is then assigned as an attribute named books to the model
        model.addAttribute("books", bookRepository.findAll());

        //the return refers to a Thymeleaf view
        return "books";
    }
}
