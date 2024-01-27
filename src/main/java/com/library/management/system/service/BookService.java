package com.library.management.system.service;

import com.library.management.system.dto.BookDTO;
import com.library.management.system.entity.Book;
import com.library.management.system.exception.BookNotFoundException;
import com.library.management.system.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {
    private BookRepository bookRepository;
    private ModelMapper mapper;

    @Cacheable("book")
    public List<BookDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> mapper.map(book, BookDTO.class))
                .toList();
    }

    public Book findBookById(final int bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book for the id: %s. not found", bookId)));
    }

    @Cacheable("book")
    public BookDTO findById(final int bookId) {
        return mapper.map(findBookById(bookId), BookDTO.class);
    }
    @Transactional
    @Caching(evict = {
            @CacheEvict(value="book", allEntries=true)})
    public BookDTO save(final BookDTO bookDTO) {
        Book book = mapper.map(bookDTO, Book.class);
        Book savedBook = bookRepository.save(book);
        return mapper.map(savedBook, BookDTO.class);
    }
    @Transactional
    @CacheEvict(value = "patron", key = "#bookDTO.id",allEntries=true)
    public BookDTO update(final BookDTO bookDTO) {
        var currentBook = this.findById(bookDTO.getId());
        Book toUpdateBook = mapper.map(bookDTO, Book.class);
        toUpdateBook.setId(currentBook.getId());
        return mapper.map(bookRepository.save(toUpdateBook), BookDTO.class);
    }
    @Transactional
    @CacheEvict(value = "book",key = "#bookId", allEntries=true)
    public void deleteById(final int bookId) {
        bookRepository.deleteById(this.findById(bookId).getId());
    }
}
