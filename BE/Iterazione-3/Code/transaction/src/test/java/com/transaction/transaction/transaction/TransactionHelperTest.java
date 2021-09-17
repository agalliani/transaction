package com.transaction.transaction.transaction;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.transaction.transaction.proposal.Proposal;
import com.transaction.transaction.proposal.StatusProposal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionHelperTest {

    /**
     * Check if the method createTransaction will be invoked
     * 
     * @throws Exception
     */
    @Test
    void whenCreateTransactionCalledVerified() throws Exception {

        TransactionHelper tranHelper = mock(TransactionHelper.class);

        Proposal prop = new Proposal(15187, "ProposalName", "Questa è una breve descrizione.",
                "andrea.galliani.29@gmail.com", "a.galliani@studenti.unibg.it", StatusProposal.PENDING);

        tranHelper.createTransaction(prop);

        verify(tranHelper, times(1)).createTransaction(prop);
    }

    /**
     * Check if the createTransaction method throws an exception when its argument
     * is null
     */
    @Test
    void givenNull_createTransaction() {
        TransactionHelper tranH = mock(TransactionHelper.class);

        Assertions.assertThrows(Exception.class, () -> {
            doThrow(Exception.class).when(tranH).createTransaction(isNull());

            tranH.createTransaction(null);

        });
    }

}
