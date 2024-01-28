package com.library.management.system.service;

import com.library.management.system.dto.BorrowRecordDTO;
import com.library.management.system.entity.Book;
import com.library.management.system.entity.Borrowing;
import com.library.management.system.entity.Patron;
import com.library.management.system.exception.BorrowRecordNotFoundException;
import com.library.management.system.repository.BorrowingRecordRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class BorrowingRecordService {
    private BorrowingRecordRepository borrowingRecordRepository;
    private ModelMapper mapper;
    private BookService bookService;

    private PatronService patronService;

    @Transactional
    public BorrowRecordDTO save(final BorrowRecordDTO borrowRecordDTO) {
        Borrowing newBorrowing = borrowingRecordRepository.save(getBorrowRecord(borrowRecordDTO));
        return mapper.map(newBorrowing, BorrowRecordDTO.class);
    }

    private Borrowing getBorrowRecord(BorrowRecordDTO borrowRecordDTO) {
        Borrowing borrowing = mapper.map(borrowRecordDTO, Borrowing.class);
        Book book = bookService.findBookById(borrowRecordDTO.getBookId());
        Patron patron = patronService.findById(borrowRecordDTO.getPatronId());
        borrowing.setBook(book);
        borrowing.setPatron(patron);
        return borrowing;
    }

    @Transactional
    public BorrowRecordDTO update(final BorrowRecordDTO borrowRecordDTO) {
        Borrowing currentBorrowRecord = findBorrowById(borrowRecordDTO.getBorrowingId());
        Borrowing toUpdateBorrowRecord = mapper.map(borrowRecordDTO, Borrowing.class);
        currentBorrowRecord.setBorrowingId(toUpdateBorrowRecord.getBorrowingId());
        currentBorrowRecord.setReturnDate(toUpdateBorrowRecord.getReturnDate());
        return mapper.map(borrowingRecordRepository.save(currentBorrowRecord), BorrowRecordDTO.class);
    }

    public BorrowRecordDTO findById(final int borrowRecordId) {
        return mapper.map(findBorrowById(borrowRecordId), BorrowRecordDTO.class);
    }

    public Borrowing findBorrowById(final int borrowRecordId) {
        return borrowingRecordRepository.findById(borrowRecordId).orElseThrow(() -> new BorrowRecordNotFoundException(String.format("Borrow Record for the id: %s. not found", borrowRecordId)));
    }
}
