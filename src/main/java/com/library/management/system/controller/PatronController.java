package com.library.management.system.controller;

import com.library.management.system.dto.PatronDTO;
import com.library.management.system.service.PatronService;
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
@Tag(name = "Patron Record Controller", description = "API's to insert,update,delete,get and get all Patron's record")
public class PatronController {

    PatronService patronService;

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Getting all patrons")})
    @GetMapping("/patrons")
    public ResponseEntity<List<PatronDTO>> getAllPatrons() {
        return new ResponseEntity<>(patronService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/patrons/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Getting single patron")})
    public ResponseEntity<PatronDTO> getPatron(@PathVariable("id") int patronId) {
        return new ResponseEntity<>(patronService.findPatronById(patronId), HttpStatus.OK);
    }

    @PostMapping("/patrons")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "inserting an single patron")})
    public ResponseEntity<PatronDTO> savePatron(@Valid @RequestBody PatronDTO patronDTO) {
        return new ResponseEntity<>(patronService.save(patronDTO), HttpStatus.CREATED);
    }

    @PutMapping("/patrons/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "updating an single patron")})
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable("id") int patronId, @Valid @RequestBody PatronDTO patronDTO) {
        patronDTO.setId(patronId);
        return new ResponseEntity<>(patronService.update(patronDTO), HttpStatus.OK);
    }

    @DeleteMapping("/patrons/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "deleting single patron")})
    public ResponseEntity deletePatron(@PathVariable("id") int patronId) {
        patronService.deleteById(patronId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
