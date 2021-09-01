package com.transaction.transaction.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.transaction.transaction.mail.EmailHelper;
import com.transaction.transaction.transaction.Transaction;
import com.transaction.transaction.transaction.TransactionHelper;
import com.transaction.transaction.transaction.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionWriter implements PriorizerGreedy<Transaction> {

    @Autowired
    TransactionService transactionService;

    @Autowired
    EmailHelper emailHelper;
    List<Transaction> buffer = new LinkedList<>();

    List<Transaction> sequenzaEsecuzione = new ArrayList<>();

    public void sendToEthereum(Transaction tran) {
        buffer.add(tran);
        writingOrderer(buffer);
        writeInBlock(tran);
        buffer.clear();
    }

    private void writeInBlock(Transaction tran) {
        // MOCK EXECUTION
        Timer timer = new Timer("Timer");
        TimerTask task = new TimerTask() {
            public void run() {
                emailHelper.sendTransactionCreationStarted(tran);
                tran.setInfo("Saved into a node. Metric: " + tran.getMetric());
                try {
                    transactionService.updateTransaction(tran);
                    System.out.println("Timer executed!");

                    emailHelper.sendTransactionCreationEnded(tran);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        timer.schedule(task, tran.getMetric());
    }

    @Override
    public void writingOrderer(List<Transaction> C) {

        // Preprocessing: ordina C in modo non decrescente
        Collections.sort(C);

        for (int j = 1; j < C.size(); j++) {
            sequenzaEsecuzione.add(C.get(j));
        }
    }
}
