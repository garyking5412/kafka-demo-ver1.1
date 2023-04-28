package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(AuthorRepository authorRepository, BookRepository bookRepository) {
		return args -> {
			Author john = authorRepository.save(new Author(null, "John Wick"));
			Author winston = authorRepository.save(new Author(null, "Winston Churchill"));
			Author yamamoto = authorRepository.save(new Author(null, "Shigekuni Yamamoto"));
			bookRepository.saveAll(List.of(new Book(null, "Book 1", 452323, john), new Book(null, "Book 2", 12, john),
					new Book(null, "Book 3", 12, winston), new Book(null, "Book 4", 43, winston), new Book(null, "Book 5", 33, yamamoto),
					new Book(null, "Book 6", 756, john), new Book(null, "Book 7", 1545642, winston) ,new Book(null, "Book 8", 23432, yamamoto),
					new Book(null, "Book 9", 457, john) ,new Book(null, "Book 10", 23424, john), new Book(null, "Book 11", 9653, yamamoto)));
			List<Book> dtb = bookRepository.findAll();
			dtb.stream().forEach(book -> System.out.println(book.getTitle()));
		};
	}
}
