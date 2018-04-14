package com.lafave.spring5webapp.bootstrap;

import com.lafave.spring5webapp.model.Book;
import com.lafave.spring5webapp.model.Author;
import com.lafave.spring5webapp.model.Publisher;
import com.lafave.spring5webapp.model.repositories.AuthorRepository;
import com.lafave.spring5webapp.model.repositories.BookRepository;
import com.lafave.spring5webapp.model.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Publisher ted = new Publisher("Ted's Publishing", "123 Whatever Lane");
        Publisher fred = new Publisher("Fred's Publishing", "1453 Other Ave");
        publisherRepository.save(ted);
        publisherRepository.save(fred);

        //Carl
        Author carl = new Author("Carl", "Hardees");
        Book ddd = new Book("Domain Driven Design", "1234", ted);
        carl.getBooks().add(ddd);
        ddd.getAuthors().add(carl);

        authorRepository.save(carl);
        bookRepository.save(ddd);

        //Ronald
        Author ronald = new Author("Ronald", "Manford");
        Book noEJB = new Book("J2EE Development without EJB", "23444", fred);
        ronald.getBooks().add(noEJB);

        authorRepository.save(ronald);
        bookRepository.save(noEJB);
    }
}
