package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.BorrowRecord;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {

}
