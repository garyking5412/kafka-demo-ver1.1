package com.example.demo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
;
	@Bean
	ApplicationRunner applicationRunner(AuthorRepository authorRepository, BookRepository bookRepository) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(5555).addService(new StatService()).build();
		server.start();
		server.awaitTermination();
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

	@Bean
	JsonMessageConverter jsonMessageConverter(){
		return new JsonMessageConverter();
	}
}
