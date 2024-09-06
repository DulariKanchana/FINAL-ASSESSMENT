package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Transaction;
import com.example.demo.repo.TransactionRepo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public void saveTransaction(Transaction transaction) {

        transactionRepo.save(transaction);
    }

    public List<Transaction> getAllTransactions() {

        return transactionRepo.findAll();
    }

    public void deleteTransaction(Long id) {

        transactionRepo.deleteById(id);
    }

    public Transaction editTransaction(Long id) {
        return transactionRepo.findById(id).orElseThrow();
    }


}