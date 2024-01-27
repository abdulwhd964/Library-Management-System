package com.library.management.system.repository;

import com.library.management.system.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Integer> {
}
