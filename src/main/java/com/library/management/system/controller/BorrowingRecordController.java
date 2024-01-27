package com.library.management.system.controller;

import com.library.management.system.dto.BorrowRecordDTO;
import com.library.management.system.service.BorrowingRecordService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Borrowing Record Controller", description = "API's to insert,update Borrowing record")
public class BorrowingRecordController {

    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "inserting borrowing record for the book and patron")})
    public ResponseEntity<BorrowRecordDTO> borrowingRecord(@RequestBody BorrowRecordDTO borrowRecordDTO){
        return new ResponseEntity<>(borrowingRecordService.save(borrowRecordDTO), HttpStatus.CREATED);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "updating return date of the borrow")})
    public ResponseEntity<BorrowRecordDTO> returnBorrowingRecord(@RequestBody BorrowRecordDTO borrowRecordDTO){
        return new ResponseEntity<>(borrowingRecordService.update(borrowRecordDTO), HttpStatus.OK);
    }

}
