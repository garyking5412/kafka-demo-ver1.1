package com.example.demo;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Component
@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    Iterable<Author> authors(){
        return authorRepository.findAll();
    }
    @QueryMapping
    Optional<Author> authorById (@Argument Long id){
        return authorRepository.findById(id);
    }
}
