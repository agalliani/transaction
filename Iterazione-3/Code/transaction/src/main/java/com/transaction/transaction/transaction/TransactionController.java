package com.transaction.transaction.transaction;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
    @PostMapping(value="/transactions")
    public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction) {
        try {
            transactionService.createTransaction(transaction);

            return ResponseEntity.ok("Transaction succesfully created!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    
    
    
}
