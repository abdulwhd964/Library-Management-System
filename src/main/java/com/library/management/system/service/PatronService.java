package com.library.management.system.service;

import com.library.management.system.dto.PatronDTO;
import com.library.management.system.entity.Patron;
import com.library.management.system.exception.PatronNotFoundException;
import com.library.management.system.repository.PatronRepository;
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
public class PatronService {
    private PatronRepository patronRepository;
    private ModelMapper mapper;
    @Transactional
    @Caching(evict = {
            @CacheEvict(value="patron", allEntries=true)})
    public PatronDTO save(final PatronDTO patronDTO) {
        Patron patron = mapper.map(patronDTO, Patron.class);
        Patron savedPatron = patronRepository.save(patron);
        return mapper.map(savedPatron, PatronDTO.class);
    }

    @Cacheable("patron")
    public Patron findById(final int patronId) {
        return patronRepository.findById(patronId).orElseThrow(() -> new PatronNotFoundException(String.format("Patron for the id: %s. not found", patronId)));
    }

    public PatronDTO findPatronById(final int patronId) {
        return mapper.map(findById(patronId), PatronDTO.class);
    }


    @Cacheable("patron")
    public List<PatronDTO> findAll() {
        return patronRepository.findAll().stream().map(patron -> mapper.map(patron, PatronDTO.class)).toList();
    }
    @Transactional
    @CacheEvict(value = "patron", key = "#patronDTO.id",allEntries=true)
    public PatronDTO update(final PatronDTO patronDTO) {

        var currentPatron = this.findById(patronDTO.getId());
        Patron toUpdatePatron = mapper.map(patronDTO, Patron.class);
        toUpdatePatron.setId(currentPatron.getId());
        return mapper.map(patronRepository.save(toUpdatePatron), PatronDTO.class);
    }
    @Transactional
    @CacheEvict(value = "patron",key = "#patronId", allEntries=true)
    public void deleteById(final int patronId) {
        patronRepository.deleteById(this.findById(patronId).getId());
    }
}
