package com.transaction.transaction.proposal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProposalService {

    private List<Proposal> proposals = new ArrayList<>(
        Arrays.asList(
            new Proposal(1, "andrea", "Questa è una breve descrizione.")
        )
    );

    public void createProposal(Proposal prop){
        proposals.add(prop);
    }

    public List<Proposal> getAllProposals() {
        return proposals;
    }
    
}
