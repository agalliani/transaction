package com.transaction.transaction.algoritmo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.transaction.transaction.proposal.Proposal;
import com.transaction.transaction.proposal.StatusProposal;
import com.transaction.transaction.transaction.Transaction;
import com.transaction.transaction.transaction.TransactionHelper;

import org.junit.jupiter.api.Test;

public class TransactionWriterTest {

    /**
     * Check if the method is correctly invoked
     */
    @Test
    void verifySendToEthereum() {

        TransactionWriter transactionWriter = mock(TransactionWriter.class);

        TransactionHelper tHelper = new TransactionHelper();

        Proposal prop = new Proposal(15187, "ProposalName", "Questa è una breve descrizione.",
                "andrea.galliani.29@gmail.com", "a.galliani@studenti.unibg.it", StatusProposal.PENDING);

        Transaction tran = tHelper.buildTransaction(prop);

        transactionWriter.sendToEthereum(tran);

        verify(transactionWriter).sendToEthereum(tran);

    }

    @Test
    void testWritingOrderer() {

        List<Transaction> elenco_transazioni = new ArrayList<>();

        TransactionHelper tHelper = new TransactionHelper();

        // popolo la lista di transazioni
        for (int i = 0; i < 10; i++) {
            elenco_transazioni
                    .add(tHelper.buildTransaction(new Proposal(15187, "ProposalName", "Questa è una breve descrizione.",
                            "andrea.galliani.29@gmail.com", "a.galliani@studenti.unibg.it", StatusProposal.PENDING)));
        }

        Collections.shuffle(elenco_transazioni);

        TransactionWriter tWriter = mock(TransactionWriter.class);
        tWriter.writingOrderer(elenco_transazioni);
        verify(tWriter).writingOrderer(elenco_transazioni);


    }
}
