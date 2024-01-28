package com.library.management.system.service;

import com.library.management.system.dto.BorrowDTO;
import com.library.management.system.entity.Book;
import com.library.management.system.entity.Borrow;
import com.library.management.system.entity.Patron;
import com.library.management.system.exception.BorrowRecordNotFoundException;
import com.library.management.system.repository.BorrowRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class BorrowService {
    private BorrowRepository borrowingRecordRepository;
    private ModelMapper mapper;
    private BookService bookService;
    private PatronService patronService;

    @Transactional
    public BorrowDTO save(final BorrowDTO borrowDTO) {
        Borrow newBorrow = borrowingRecordRepository.save(getBorrowRecord(borrowDTO));
        return mapper.map(newBorrow, BorrowDTO.class);
    }

    @Transactional
    public BorrowDTO update(final BorrowDTO borrowDTO) {
        Borrow currentBorrowRecord = findBorrowById(borrowDTO.getBorrowingId());
        Borrow toUpdateBorrowRecord = mapper.map(borrowDTO, Borrow.class);
        currentBorrowRecord.setBorrowingId(toUpdateBorrowRecord.getBorrowingId());
        currentBorrowRecord.setReturnDate(toUpdateBorrowRecord.getReturnDate());
        return mapper.map(borrowingRecordRepository.save(currentBorrowRecord), BorrowDTO.class);
    }

    public Borrow findBorrowById(final int borrowRecordId) {
        return borrowingRecordRepository.findById(borrowRecordId)
                .orElseThrow(() ->
                        new BorrowRecordNotFoundException(String.format("Borrow Record for the id: %s. not found",
                                borrowRecordId)));
    }

    private Borrow getBorrowRecord(BorrowDTO borrowDTO) {
        Borrow borrow = mapper.map(borrowDTO, Borrow.class);
        Book book = bookService.findBookById(borrowDTO.getBookId());
        Patron patron = patronService.findById(borrowDTO.getPatronId());
        borrow.setBook(book);
        borrow.setPatron(patron);
        return borrow;
    }
}
