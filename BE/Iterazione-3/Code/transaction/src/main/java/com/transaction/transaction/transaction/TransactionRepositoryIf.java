package com.transaction.transaction.transaction;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepositoryIf extends CrudRepository<Transaction, Integer> {
    
}
