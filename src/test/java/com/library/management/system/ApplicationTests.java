package com.library.management.system;

import com.library.management.system.entity.Book;
import com.library.management.system.repository.BookRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {

	@MockBean
	private BookRepository bookRepository;

	@Test
	void testSaveBook() {
		Book book = new Book(0, "Abdul", "C", new Date(),"1234",new HashSet<>());
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		assertEquals(book, bookRepository.save(book));
	}

	@Test
	@Order(1)
	void testGetAllBook() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book(0, "Abdul", "C", new Date(),"1234",new HashSet<>()));
		bookList.add(new Book(0, "Abdul", "C", new Date(),"1234",new HashSet<>()));

		Mockito.when(bookRepository.findAll()).thenReturn(bookList);
		assertEquals(2, bookRepository.findAll().size());
	}

	@Test
	@Order(2)
	void testGetBook() {
		Book book = new Book(0, "Abdul", "C", new Date(),"1234",new HashSet<>());
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		assertEquals(book, bookRepository.save(book));
	}

}
