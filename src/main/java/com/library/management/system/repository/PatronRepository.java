package com.library.management.system.repository;


import com.library.management.system.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron,Integer> {
}
