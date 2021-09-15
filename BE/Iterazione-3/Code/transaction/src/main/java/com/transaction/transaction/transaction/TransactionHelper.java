package com.transaction.transaction.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import com.transaction.transaction.algoritmo.TransactionWriter;
import com.transaction.transaction.proposal.Proposal;

@Component
public class TransactionHelper {

    @Autowired
    private TransactionService tranService;

    @Autowired
    private TransactionWriter tranWriter;

    AtomicInteger idGen = new AtomicInteger(1000){
        
    };

    public void createTransaction(Proposal prop) throws Exception {

        Transaction nuova = new Transaction(
            idGen.getAndIncrement(),
            prop.getId(),
            prop.getEmail(),
            prop.getRecipient(),
            prop.getDescription(),
            "Preprocessing",
            calcMetric(prop)
        );

        tranService.createTransaction(nuova);
        tranWriter.sendToEthereum(nuova);
    }

    private Integer calcMetric(Proposal prop) {
        return 10000 + prop.getDescription().length() * 173;
    }
}
