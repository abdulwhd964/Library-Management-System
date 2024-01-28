package com.library.management.system.controller;

import com.library.management.system.dto.BookDTO;
import com.library.management.system.entity.Book;
import com.library.management.system.exception.BookNotFoundException;
import com.library.management.system.service.BookService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@Tag(name = "Book Controller", description = "API's to insert,update,delete,get and get all Book's record")
public class BookController {
    private BookService bookService;

    /**
     * return an list of all books
     * @return
     */
    @GetMapping("/books")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Getting all books")})
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    /**
     * return single book
     * @param bookId
     * @return
     * @throws BookNotFoundException
     */
    @GetMapping("/books/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Getting single book")})
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") int bookId) throws BookNotFoundException {
        return new ResponseEntity<>(bookService.findById(bookId), HttpStatus.OK);
    }

    /**
     * insert an book
     * @param bookDTO
     * @return
     */
    @PostMapping("/books")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "inserting an book")})
    public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.save(bookDTO), HttpStatus.CREATED);
    }

    /**
     * update an book
     * @param bookId
     * @param bookDTO
     * @return
     */
    @PutMapping("/books/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "updating an book")})
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") int bookId, @Valid @RequestBody BookDTO bookDTO) {
        bookDTO.setId(bookId);
        return new ResponseEntity<>(bookService.update(bookDTO), HttpStatus.OK);
    }

    /**
     * delete an book
     * @param bookId
     * @return
     */
    @DeleteMapping("/books/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "deleting an book")})
    public ResponseEntity deleteBook(@PathVariable("id") int bookId) {
        bookService.deleteById(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
