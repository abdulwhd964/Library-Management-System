package com.library.management.system.controller;

import com.library.management.system.dto.BorrowDTO;
import com.library.management.system.service.BorrowService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Borrow Record Controller", description = "API's to insert,update Borrow record")
public class BorrowingRecordController {

    private BorrowService borrowService;

    /**
     * insert an borrow record for an book and patron
     * @param borrowDTO
     * @return
     */
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "inserting borrowing record for the book and patron")})
    public ResponseEntity<BorrowDTO> borrowingRecord(@RequestBody BorrowDTO borrowDTO){
        return new ResponseEntity<>(borrowService.save(borrowDTO), HttpStatus.CREATED);
    }

    /**
     * update an borrow with the return date
     * @param borrowDTO
     * @return
     */
    @PutMapping("/return/{bookId}/patron/{patronId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "updating return date of the borrow")})
    public ResponseEntity<BorrowDTO> returnBorrowingRecord(@RequestBody BorrowDTO borrowDTO){
        return new ResponseEntity<>(borrowService.update(borrowDTO), HttpStatus.OK);
    }

}
