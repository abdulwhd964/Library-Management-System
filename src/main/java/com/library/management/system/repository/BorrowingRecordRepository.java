package com.library.management.system.repository;

import com.library.management.system.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<Borrowing,Integer> {
}
