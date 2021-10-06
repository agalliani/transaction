package com.transaction.transaction.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepositoryIf transactionRepository;

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        transactionRepository.findAll().forEach(transactions::add);
        return transactions;
    }

    public void createTransaction(Transaction transaction) throws Exception {

        if (!transactionRepository.existsById(transaction.getId())) {

            transactionRepository.save(transaction);
            
        } else {
            String message = "A transaction with this ID already exists!";
            throw new Exception(message);
        }

    }

    //change the info field of a transaction after his saving on the chain
    public void updateTransaction(Transaction tran) throws Exception {
        // non existing elements can't be updated
        if (transactionRepository.existsById(tran.getId())) {
            // overwrite the existing element in the DB. The id is unique
            transactionRepository.save(tran);
        } else {
            String message = "Non existing elements can't be updated!";
            throw new NotFoundException(message);
        }

    }

}
